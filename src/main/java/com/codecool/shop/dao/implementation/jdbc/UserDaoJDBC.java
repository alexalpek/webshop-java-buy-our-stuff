package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;
import lombok.Cleanup;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBC extends DaoJDBC implements UserDao {

    @Override
    public void add(String name, String password) {
        String query = "INSERT INTO account (name, password, cart_id) VALUES (?, ?, ?);";

        CartDao cartDao = DaoController.getCartDao();
        Cart cart = new Cart("USD");
        cartDao.add(cart);

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            stmt.setString(1, name);
            stmt.setString(2, hashedPassword);
            stmt.setInt(3, cart.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }
    }

    @Override
    public User find(String username, String password) {
        String query = "SELECT id, password, cart_id FROM account WHERE name = (?)";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && BCrypt.checkpw(password, rs.getString("password"))) {
                User user = new User(username, rs.getInt("cart_id"));
                user.setId(rs.getInt("id"));
                return user;
            }
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }

        throw new DataNotFoundException("No such user");
    }

    @Override
    public boolean isNameAvailable(String username) {
        String query = "SELECT id, password FROM account WHERE name = (?)";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }
        return true;
    }
}

