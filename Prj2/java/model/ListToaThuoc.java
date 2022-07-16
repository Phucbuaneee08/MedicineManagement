package Prj2.java.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ListToaThuoc {
    
    public ObservableList<ToaThuoc> getListToa() {
        return this.list;
    }
    public ObservableList<ToaThuoc> getListThuoc() {

        for(ToaThuoc x : this.list) {
         
                this.listToaThuoc.add(x);
      
        }
        return this.listToaThuoc;
    }

    private ObservableList<ToaThuoc> list = FXCollections.observableArrayList();
    private ObservableList<ToaThuoc> listToaThuoc = FXCollections.observableArrayList();
}