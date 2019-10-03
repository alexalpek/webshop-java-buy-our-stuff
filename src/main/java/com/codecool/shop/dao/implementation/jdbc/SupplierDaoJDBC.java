package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.DataNotFoundException;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC extends DaoJDBC implements SupplierDao {

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO supplier (name, description) VALUES (?, ?) RETURNING id;";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getDescription());
            @Cleanup ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                supplier.setId(id);
            } else {
                throw new DataNotFoundException("Supplier object received no id");
            }
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM supplier WHERE id = " + id + ";";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Supplier supplier = new Supplier(name, description);
                supplier.setId(id);
                return supplier;
            }
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }

        throw new DataNotFoundException("No such supplier");
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM supplier WHERE id = " + id + ";";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();

            stmt.execute(query);
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier;";
        List<Supplier> suppliers = new ArrayList<>();

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Supplier supplier = new Supplier(name, description);
                supplier.setId(id);
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            throw new DataSourceException("Database not reachable", e);
        }

        return suppliers;
    }
}
