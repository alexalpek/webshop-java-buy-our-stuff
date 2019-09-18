package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.DaoController;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.ProductDao;
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
                "VALUES (?, ?, ?);";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, lineItem.getProduct().getId());
            stmt.setInt(2, lineItem.getCartId());
            stmt.setInt(3, lineItem.getQuantity());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public List<LineItem> getBy(Cart cart) {
        ProductDao productDataStore = DaoController.getProductDao();
        int cartId = cart.getId();
        String query = "SELECT * FROM line_item WHERE cart_id = " + cartId + ";";
        List<LineItem> result = new ArrayList<>();

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Product product = productDataStore.find(productId);
                LineItem lineItem = new LineItem(product, quantity, cart);
                result.add(lineItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
