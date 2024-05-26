package com.example.dbproject;

public class Product {
    String name, id;
    double price, quantity;

    public Product(String name, String id, double price, double quantity) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }
}
