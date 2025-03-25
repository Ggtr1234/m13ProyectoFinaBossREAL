package com.example.projectem13finaboss.model;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = List.of(
            new Product(1, "Producto1", 20),
            new Product(2, "Producto2", 30),
            new Product(3, "Producto3", 40),
            new Product(4, "Producto4", 50),
            new Product(5, "Producto5", 60)
    );;

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(int id, String name, int price){
        products.add(new Product(id,name,price));
    }

    public void removeProduct(int id){
        Product product = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                product = products.get(i);
            }
        }
        products.remove(product);
    }

}
