package Prj2.model;

import java.util.ArrayList;
import Prj2.DAO.CrawlInfo;
import javafx.scene.text.Text;

import java.io.IOException;

public class DungCu extends Product {


    private String use;
    private int quantity;
    public DungCu(int productID, String name, String unit, int quantity, String use) {
        super(productID, name, unit);
        this.use = use;
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }
    @Override
    public ArrayList<Text> getInfo() {
        ArrayList<Text> x = super.getInfo();
        CrawlInfo info;
        try {
            info = new CrawlInfo(this.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        x.add(new Text("Số Lượng: "+ this.quantity));
        x.addAll(info.listText);
        return x;
    }

}