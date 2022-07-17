package Prj2.java.model;

public class ThuocTrongToa extends Product {
    private final String lieu;
    public String getLieu() {
        return lieu;
    }

    public ThuocTrongToa(int productID,String name,String unit, String lieu) {
        super(productID,name,unit);
        this.lieu = lieu;
    }

}