package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.DataNotFoundException;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.util.Error;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC extends DaoJDBC implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO product_category (name, department, description) " +
                "VALUES (?, ?, ?) RETURNING id;";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDepartment());
            stmt.setString(3, category.getDescription());
            @Cleanup ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                category.setId(id);
            } else {
                throw new DataNotFoundException(Error.NO_CATEGORY_ID);
            }
        } catch (SQLException e) {
            throw new DataSourceException(Error.DATABASE_IS_UNREACHABLE, e);
        }
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM product_category WHERE id = " + id + ";";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String name = rs.getString("name");
                String department = rs.getString("department");
                String description = rs.getString("description");
                ProductCategory productCategory = new ProductCategory(name, department, description);
                productCategory.setId(id);
                return productCategory;
            }
        } catch (SQLException e) {
            throw new DataSourceException(Error.DATABASE_IS_UNREACHABLE, e);
        }

        throw new DataNotFoundException(Error.NO_SUCH_CATEGORY);
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product_category WHERE id = " + id + ";";

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();

            stmt.execute(query);
        } catch (SQLException e) {
            throw new DataSourceException(Error.DATABASE_IS_UNREACHABLE, e);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM product_category;";
        List<ProductCategory> categories = new ArrayList<>();

        try {
            @Cleanup Connection conn = getConnection();
            @Cleanup Statement stmt = conn.createStatement();
            @Cleanup ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String description = rs.getString("description");
                ProductCategory productCategory = new ProductCategory(name, department, description);
                productCategory.setId(id);
                categories.add(productCategory);
            }
        } catch (SQLException e) {
            throw new DataSourceException(Error.DATABASE_IS_UNREACHABLE, e);
        }

        return categories;
    }
}
