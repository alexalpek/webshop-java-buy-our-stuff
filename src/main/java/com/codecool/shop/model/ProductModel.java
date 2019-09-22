package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class ProductModel extends BaseModel {

    protected String name;
    protected String description;

    public ProductModel(String name) {
        this.name = name;
    }

    public ProductModel(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
