package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DataNotFoundException;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.model.Cart;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJDBC extends DaoJDBC implements CartDao {

    @Override
    public void add(Cart cart) {
        String query = "INSERT INTO cart (currency) VALUES (?) RETURNING id";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, cart.getCurrency().getCurrencyCode());
            @Cleanup ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                cart.setId(id);
            } else {
                throw new DataNotFoundException("Cart object received no id");
            }
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
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
                Cart cart = new Cart("USD");
                cart.setId(id);
                return cart;
            }
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }

        throw new DataNotFoundException("No such supplier");
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM cart WHERE id = " + id + ";";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();

            stmt.execute(query);
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
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
                Cart cart = new Cart("USD");
                cart.setId(id);
                cartList.add(cart);
            }
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }

        return cartList;
    }
}
