package com.example.dbproject;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class addProductRowController {
    @FXML
    Text name, weight, uprice, upurchase, quantity;
    TextField productName, preset, tquantity, sellingPrice, purchasePrice, tweight;
    ComboBox<String> typeCombo;
    VBox productsVbox;
    Parent root;
    String type;
    AddProduct p;
    @FXML
    public void handleDelete() {
        productsVbox.getChildren().remove(root);
        addProductController.products.remove(p);
    }
    @FXML
    public void handleEdit() {
        productName.setText(p.name);
        preset.setText(p.preset + "");
        tquantity.setText(p.quantity + "");
        tweight.setText(p.weight + "");
        purchasePrice.setText(p.purchase + "");
        sellingPrice.setText(p.price + "");
        typeCombo.setValue(p.type.equals("W") ? "Sold by Weight" : "Sold by Quantity");

        handleDelete();
    }
}
