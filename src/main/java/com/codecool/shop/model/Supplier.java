package com.codecool.shop.model;

public class Supplier extends ProductModel {

    public Supplier(String name, String description) {
        super(name, description);
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