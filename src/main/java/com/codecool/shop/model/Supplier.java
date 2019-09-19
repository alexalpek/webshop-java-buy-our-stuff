package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Supplier extends ProductModel {

    private List<Product> products;

    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return String.format("%1$s={" +
                        "id: %2$d, " +
                        "name: %3$s, " +
                        "description: %4$s}",
                this.getClass().getSimpleName(),
                this.id,
                this.name,
                this.description
        );
    }
}