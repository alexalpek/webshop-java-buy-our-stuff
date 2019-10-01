package com.codecool.shop.model;


import lombok.Getter;

@Getter
public class User extends BaseModel {

    private String name;
    private int cartId;

    public User(String name, int cartId) {
        this.name = name;
        this.cartId = cartId;
    }
}
