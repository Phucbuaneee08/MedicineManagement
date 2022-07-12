package Prj2.controller;

import Prj2.model.Product;
import Prj2.model.ThuocTrongToa;
import Prj2.model.ToaThuoc;
import Prj2.save.SaveToExcel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.Date;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ToaThuocViewController implements Initializable{
    @FXML private TableView<ToaThuoc> TVToaThuoc;
    
    @FXML private AnchorPane apToaThuoc;

    @FXML private TableColumn<ToaThuoc, LocalDate> endDate;

    @FXML private TableColumn<ToaThuoc, String> name;

    @FXML private TableColumn<ToaThuoc, Integer> presID;

    @FXML private TableColumn<ToaThuoc, LocalDate> startedDate;
    public int lastIndexToa = 0 ;
    @FXML
    private VBox vbToaThuoc ;
    @FXML private  Button btnToaThuoc;
    private final TuThuocController tuThuocController;
    public ToaThuocViewController(Controller controller, TuThuocController tuThuocController) throws IOException {

        this.listToa.addAll(new SaveToExcel().getToaThuocFromExcel());
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/Prj2/View/ToaThuoc.fxml"));
        pane.setController(this);
        AnchorPane anchorPane = pane.load();
        controller.setToaThuocView(anchorPane);
        btnToaThuoc.setOnAction(event -> showAddToaThuoc());
        this.tuThuocController = tuThuocController;
    }
    private ArrayList<ThuocTrongToa> listToa1 = new ArrayList<>();
    Date date1 = new Date();
    ObservableList<ToaThuoc> listToa = FXCollections.observableArrayList(
        );
    public ObservableList<ToaThuoc> getListToa(){
            return this.listToa;
    }
    public int getLastIndexToa(){
        return this.getListToa().get(listToa.size()-1).getPresID();
    }
    public void showToaThuoc(){
        TVToaThuoc.setRowFactory(new Callback<>() {
            @Override
            public TableRow<ToaThuoc> call(TableView<ToaThuoc> tableView) {
                return new TableRow<>() {
                    @Override
                    public void updateItem(ToaThuoc item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null){
                            setStyle("");
                        } else if (item.status()==1) {
                            setStyle("-fx-background-color: #ff1a1a");
                        } else if (item.status() == -1) {
                            setStyle("-fx-background-color: #95A7C0;");
                        } else if (item.status() == 0) {
                            setStyle("-fx-background-color: #30AA3C;");
                        } else {
                            setStyle("");
                        }
                    }
                };
            }
        });
        presID.setCellValueFactory(new PropertyValueFactory<>("presID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        startedDate.setCellValueFactory(cellData ->{
             return new SimpleObjectProperty<>(((ToaThuoc)cellData.getValue()).getStartedDate());
        }
    );
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        TVToaThuoc.setItems(listToa);
    }
    private void addButtonToTable() throws IOException{
        TableColumn<ToaThuoc, Void> colBtn = new TableColumn<>("");
        colBtn.setMaxWidth(800);
        Callback<TableColumn<ToaThuoc, Void>, TableCell<ToaThuoc, Void>> cellFactory = new Callback<TableColumn<ToaThuoc, Void>, TableCell<ToaThuoc, Void>>() {
            @Override
            public TableCell<ToaThuoc, Void> call(final TableColumn<ToaThuoc, Void> param) {
                final TableCell<ToaThuoc, Void> cell = new TableCell<ToaThuoc, Void>() {

                    private final MenuButton btn = new MenuButton("");
                    {   
                        btn.setStyle("-fx-background-color:");
                        MenuItem m3 = new MenuItem("detail");
                        MenuItem m1 = new MenuItem("delete");
                        btn.getItems().add(m3);
                        btn.getItems().add(m1);
                        //set action for edit button
                        m1.setOnAction(event->{
                            ToaThuoc toaThuoc = getTableRow().getItem();
                            listToa.remove(toaThuoc);
                        });
                        m3.setOnAction(event->{
                            ToaThuoc toaThuoc = getTableRow().getItem();
                            ShowDetailTTController showDetailTTController = new ShowDetailTTController(ToaThuocViewController.this ,toaThuoc) ;
                            showDetailTTController.setTextField(toaThuoc.showInfo());
                            showDetailTTController.showStage();
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        TVToaThuoc.getColumns().add(colBtn);
    }
    public ObservableList<Product> getListThuocFromTuThuoc(){
        return FXCollections.observableArrayList(tuThuocController.main.getListThuoc());
    }
    // lay list tu tu thuoc
    @FXML
    public void showAddToaThuoc(){
        AddToaThuoc addToaThuoc = new AddToaThuoc(this);
        addToaThuoc.showStage();
    } //show stage add toa

    public void thongBaoUongThuoc(){
        int soToaDangTrongThoiGianUong = 0;
        for(ToaThuoc x : listToa){
            if(x.status() == 0){
                soToaDangTrongThoiGianUong ++;
            }
        }
        if(soToaDangTrongThoiGianUong != 0) {
            String context = "Bạn đang có " +soToaDangTrongThoiGianUong +" toa (đơn) thuốc đang trong thời gian thực hiện vui lòng kiểm tra trong phần TOA THUỐC (toa màu xanh)";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cảnh báo thuốc");
            alert.setContentText(context);
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        showToaThuoc();
        try {
            addButtonToTable();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}