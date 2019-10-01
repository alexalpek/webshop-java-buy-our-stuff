package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {

    void add(String name, String password);

    User find(String name, String password);

    boolean isNameAvailable(String username);
}
