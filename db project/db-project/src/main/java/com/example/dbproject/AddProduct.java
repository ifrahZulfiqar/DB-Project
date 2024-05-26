package com.example.dbproject;

public class AddProduct {
    String name, type;
    double purchase, price, weight;
    int quantity, preset;

    public AddProduct(String name, String type, double purchase, double price, double weight, int quantity, int preset) {
        this.name = name;
        this.type = type;
        this.purchase = purchase;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.preset = preset;
    }
}
