package com.example.projectem13finaboss.model;

public class Product {
    private int id;
    private String name;
    private int price;

    public Product(int i, String name, int price) {
        this.id = i;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
