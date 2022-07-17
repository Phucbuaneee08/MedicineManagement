package Prj2.model;

import Prj2.save.SaveToExcel;
import Prj2.save.SaveToExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.IOException;
import java.util.ArrayList;


public class TuThuoc {
    
    public TuThuoc() {
        try {
            ArrayList<Product> excelList = new SaveToExcel().getExcelFileDemo();
            for(Product t: excelList) {
                list.add(t);
                if(t instanceof DungCu){
                        this.listDC.add(t);
                    }
                else if(t instanceof Thuoc){
                    this.listThuoc.add(t);
                }


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int getLastIndexThuoc(){
        return this.getListThuoc().get(listThuoc.size()-1).getProductID();
    }
    public int getLastIndexDungCu(){
        return this.getListDC().get(listDC.size()-1).getProductID();
    }
    public ObservableList<Product>  getList() {
        return this.list;
    }
    public ObservableList<Product> getListThuoc() {
       
        return this.listThuoc;
    }
    public ObservableList<Product> getListDC() {

    
        return this.listDC;
    }

    private ObservableList<Product> list = FXCollections.observableArrayList();
    private ObservableList<Product> listThuoc = FXCollections.observableArrayList();
    private ObservableList<Product> listDC = FXCollections.observableArrayList();
}