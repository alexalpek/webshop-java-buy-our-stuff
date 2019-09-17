package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJDBC extends DaoJDBC implements CartDao {
    //account ID, Currency String, ID.
    private int userId = 1; //TODO: get logged in user's id!

    @Override
    public void add(Cart cart) {
        String query = "INSERT INTO cart (account_id, currency) VALUES (?, ?) RETURNING id";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, userId);
            stmt.setString(2, cart.getCurrency().getDisplayName());
            @Cleanup ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                cart.setId(id);
            } else {
                throw new RuntimeException("Cart object received no id"); // TODO
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cart find(int id) {
        String query = "SELECT * FROM cart WHERE id = " + id + ";";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                Cart cart = new Cart("USD", userId); //TODO
                cart.setId(id);
                return cart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM cart WHERE id = " + id + ";";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();

            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cart> getAll() {
        String query = "SELECT * FROM cart";
        List<Cart> cartList = new ArrayList<>();

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                int id = rs.getInt("id");
                Cart cart = new Cart("USD", userId); //TODO
                cart.setId(id);
                cartList.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    }
}
