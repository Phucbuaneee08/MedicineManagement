package Prj2.java.inteface;

import Prj2.java.model.Product;

public interface EditAble extends Addable {
    void actionEdit(Product x);
    void setTextField(Product x);
}