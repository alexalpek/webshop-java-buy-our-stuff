package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ProductCategory extends ProductModel {

    private String department;
    private List<Product> products;

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return String.format("%1$s={" +
                        "id: %2$d, " +
                        "name: %3$s, " +
                        "department: %4$s, " +
                        "description: %5$s}",
                this.getClass().getSimpleName(),
                this.id,
                this.name,
                this.department,
                this.description);
    }
}