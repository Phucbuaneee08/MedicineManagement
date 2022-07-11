package Prj2.controller;

import Prj2.inteface.EditAble;
import Prj2.model.Product;
import Prj2.model.Thuoc;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddMedController extends AddAbstractClass implements EditAble {
    private final Stage stage ;
    private final TuThuocController controller;
    @FXML
    private Button btnSave;
    @FXML
    private TextField tfEffect;
    @FXML
    private DatePicker tfHSD;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfUnit;
    public AddMedController(TuThuocController controller){
        this.controller = controller;
        stage = new Stage();
        this.loadStage();
        btnSave.setOnAction(event ->actionAdd());
    }
    public AddMedController(TuThuocController controller, Product x){
        this.controller = controller;
        stage = new Stage();
        // TO
        loadStage();
        btnSave.setOnAction(event ->actionEdit(x));
    }   
    @Override
    public void showStage(){
        super.showStage(this.stage);
    }
    @Override
    public void loadStage() {
        super.loadStage("/Prj2/View/AddMed.fxml",this.stage);
    }
    @Override
    public void actionAdd() {
        if(tfName.getText().isEmpty() ||tfQuantity.getText().isEmpty()||tfUnit.getText().isEmpty()||tfEffect.getText().isEmpty()){ 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hãy điền vào hết chỗ trống");
            alert.showAndWait();
        }
        else{
            if(tfHSD.getValue() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Hãy điền vào hết chỗ trống");
                alert.showAndWait();
            }
            else{
                int rs = controller.main.getRsThuoc();
                Thuoc thuoc = new Thuoc(rs+1,tfName.getText(),tfUnit.getText(),Integer.parseInt(tfQuantity.getText()),tfHSD.getValue(),tfEffect.getText());
                controller.main.getList().add(thuoc);
                controller.main.setRsThuoc(rs+1);
                stage.close();
            }
        }
    }
    @Override
    public void actionEdit(Product x){
        x.setName(tfName.getText());
        x.setUnit(tfUnit.getText());
        ((Thuoc)x).setQuantity(Integer.parseInt(tfQuantity.getText()));
        ((Thuoc)x).setExpiredDate(tfHSD.getValue());
        ((Thuoc)x).setEffect(tfEffect.getText());
        controller.table.refresh();
        stage.close();
    }
    public void setTextField(Product x){
        tfName.setText(x.getName());
        tfQuantity.setText(((Thuoc)x).getQuantity()+"");
        tfUnit.setText(x.getUnit());
        tfHSD.setValue(((Thuoc)x).getExpiredDate());
        tfEffect.setText(((Thuoc)x).getEffect());
    }
}