package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductCategory extends ProductModel {

    private String department;

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
    }

    public String toString() {
        return String.format("%1$s={" +
                        "id: %2$d, " +
                        "name: %3$s, " +
                        "department: %4$s, " +
                        "description: %5$s}",
                getClass().getSimpleName(),
                id,
                name,
                department,
                description
        );
    }
}