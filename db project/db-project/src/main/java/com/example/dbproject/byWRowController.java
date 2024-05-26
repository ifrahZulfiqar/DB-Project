package com.example.dbproject;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.function.Function;

public class byWRowController {
    @FXML
    Text byQName1, productId, price, totalPrice, amount, weight;
    double am;
    double unitAmount;
    @FXML
    TextField priceInput, weightInput;
    Text totalBill, totalBillAfterDiscount, discount, returnAmount, amountPaid;
    VBox productsVbox;
    Parent comp;
    Product p;
    @FXML
    public void handleDelete() {
        productsVbox.getChildren().remove(comp);
        placeOrderController.products.remove(p);
        totalBill.setText(placeOrderController.sumBill() + "");
        totalBillAfterDiscount.setText(placeOrderController.sumBill() - Double.parseDouble(discount.getText()) + "");
        returnAmount.setText((Double.parseDouble(amountPaid.getText()) - Double.parseDouble(totalBillAfterDiscount.getText())) + "");
    }
    @FXML
    public void handlePrice() {
        String typed = priceInput.getText();
        am = Double.parseDouble(typed);
        am = (am / Double.parseDouble(price.getText())) * unitAmount;
        amount.setText(String.format("%.2f", am));
        totalPrice.setText(typed);
        weightInput.setText("");

        p.price = Double.parseDouble(typed);
        p.quantity = am;
        totalBill.setText(placeOrderController.sumBill() + "");
        totalBillAfterDiscount.setText(placeOrderController.sumBill() - Double.parseDouble(discount.getText()) + "");
        returnAmount.setText((Double.parseDouble(amountPaid.getText()) - Double.parseDouble(totalBillAfterDiscount.getText())) + "");
    }
    @FXML
    public void handleWeight() {
        String typed = weightInput.getText();
        am = Double.parseDouble(typed);
        am = am / unitAmount * Double.parseDouble(price.getText());
        amount.setText(typed);
        totalPrice.setText(String.format("%.2f", am));
        priceInput.setText("");

        p.price = am;
        p.quantity = Double.parseDouble(typed);
        totalBill.setText(placeOrderController.sumBill() + "");
        totalBillAfterDiscount.setText(placeOrderController.sumBill() - Double.parseDouble(discount.getText()) + "");
        returnAmount.setText((Double.parseDouble(amountPaid.getText()) - Double.parseDouble(totalBillAfterDiscount.getText())) + "");
    }
}
