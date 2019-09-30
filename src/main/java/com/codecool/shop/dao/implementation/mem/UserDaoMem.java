package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoMem implements UserDao {

    Map<User, String> users = new HashMap<>();

    @Override
    public void add(String name, String password) {

    }

    @Override
    public User find(String username, String password) {
        return null;
    }
}
