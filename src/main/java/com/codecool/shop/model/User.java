package com.codecool.shop.model;


import lombok.Getter;

public class User extends BaseModel {

    @Getter
    private String name;

    public User(String name) {
        this.name = name;
    }
}
