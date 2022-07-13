package Prj2.controller;

import Prj2.inteface.EditAble;
import Prj2.model.DungCu;
import Prj2.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddDCController extends AddAbstractClass implements EditAble {
    private final Stage stage ;
    private final TuThuocController controller;

    @FXML private Button btnSave;

    @FXML private TextField tfEffect;

    @FXML private TextField tfName;

    @FXML private TextField tfQuantity;

    @FXML private TextField tfUnit;
    public AddDCController(TuThuocController controller){
        this.controller = controller;
        this.stage = new Stage();
        loadStage();
        btnSave.setOnAction(event -> actionAdd());
    }

    public AddDCController(TuThuocController controller, Product x){
        this.controller = controller;
        stage = new Stage();
        loadStage();
        btnSave.setOnAction(event ->actionEdit(x));
    }

    @Override
    public void loadStage() {
        super.loadStage("/Prj2/view/AddDC.fxml", this.stage);
    }

    @Override
    public void showStage(){
        super.showStage(this.stage);
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
            int rs = controller.main.getLastIndexDungCu();
            DungCu DungCu = new DungCu(rs+1,tfName.getText(),tfUnit.getText(),Integer.parseInt(tfQuantity.getText()),tfEffect.getText());
            controller.main.getList().add(DungCu);
     
            stage.close();
        }
    }

    @Override
    public void actionEdit(Product x){
        x.setName(tfName.getText());
        ((DungCu)x).setQuantity(Integer.parseInt(tfQuantity.getText()));
        x.setUnit(tfUnit.getText());
        ((DungCu)x).setUse(tfEffect.getText());
        controller.table.refresh();
        stage.close();
    }
    @Override
    public void setTextField(Product x){
        tfName.setText(x.getName());
        tfQuantity.setText(((DungCu)x).getQuantity()+"");
        tfUnit.setText(x.getUnit());
        tfEffect.setText(((DungCu)x).getUse());
    }
}