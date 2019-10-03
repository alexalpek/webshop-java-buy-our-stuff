package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoJDBC extends DaoJDBC implements LineItemDao {

    @Override
    public void add(LineItem lineItem) {
        String query = "INSERT INTO line_item (product_id, cart_id, quantity) " +
                "VALUES (?, ?, ?) RETURNING id;";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, lineItem.getProduct().getId());
            stmt.setInt(2, lineItem.getCartId());
            stmt.setInt(3, lineItem.getQuantity());

            @Cleanup ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                lineItem.setId(id);
            } else {
                throw new DataNotFoundException("LineItem object received no id");
            }
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }
    }

    @Override
    public void remove(LineItem lineItem) {
        int productId = lineItem.getProduct().getId();
        String query = "DELETE FROM line_item WHERE product_id = " + productId + ";";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();

            stmt.execute(query);
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }
    }

    @Override
    public void update(LineItem lineItem, int quantity) {
        int productId = lineItem.getProduct().getId();
        String query = "UPDATE line_item SET quantity = " + quantity +
                " WHERE product_id = " + productId;

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();

            stmt.execute(query);
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }
    }

    @Override
    public LineItem find(int id) {
        String query = "SELECT * FROM line_item WHERE id = " + id + ";";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                int productId = rs.getInt("product_id");
                int cartId = rs.getInt("cart_id");
                int quantity = rs.getInt("quantity");
                ProductDao productDao = DaoController.getProductDao();
                Product product = productDao.find(productId);

                LineItem lineItem = new LineItem(product, cartId, quantity);
                lineItem.setId(id);
                return lineItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<LineItem> getBy(Cart cart) {
        ProductDao productDataStore = DaoController.getProductDao();
        int cartId = cart.getId();
        String query = "SELECT * FROM line_item WHERE cart_id = " + cartId + " ORDER BY id;";
        List<LineItem> result = new ArrayList<>();

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Product product = productDataStore.find(productId);
                LineItem lineItem = new LineItem(product, cart.getId(), quantity);
                lineItem.setId(id);
                result.add(lineItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
