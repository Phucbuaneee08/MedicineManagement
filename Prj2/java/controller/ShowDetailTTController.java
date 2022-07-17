package Prj2.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Prj2.java.model.ToaThuoc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShowDetailTTController extends ShowableAbstractClass{
    private  Stage stage ;
    private ToaThuocViewController controller;
    @FXML
    private VBox vbDetail;
    public ShowDetailTTController(ToaThuocViewController presController){
        this.controller = presController;
        stage = new Stage();
        this.loadStage();
    }
    @Override
    public void loadStage() {
        super.loadStage("/Prj2/resource/view/detailToaThuoc.fxml",this.stage);
    }
    @Override
    public void showStage(){
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    void show(ArrayList<Text> arrayList){
        for(Text i : arrayList){
            i.setWrappingWidth(vbDetail.getPrefWidth() - 30);
            vbDetail.getChildren().add(i);
        }
        vbDetail.setSpacing(5);
    }
    
}
