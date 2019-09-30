package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class UserDaoMem implements UserDao {

    Map<User, String> users = new HashMap<>();

    @Override
    public void add(String name, String password) {
        if (isUsernameAvailable(name)) {
            User user = new User(name);
            user.setId(users.size() + 1);
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
            users.put(user, hashed);
        }
    }

    @Override
    public User find(String name, String password) {
        return users
                .keySet()
                .stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private boolean isUsernameAvailable(String name) {
        return users
                .keySet()
                .stream()
                .map(User::getName)
                .noneMatch(username -> username.equals(name));
    }
}
