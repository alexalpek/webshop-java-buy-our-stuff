package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.util.DatabaseHelper;

import java.sql.*;
import java.util.Map;

public abstract class DaoJDBC {

    private static String DATABASE;
    private static String USERNAME;
    private static String PASSWORD;

    static {
        try {
            Map<String, String> credentials = DatabaseHelper.getCredentials();
            USERNAME = credentials.get("user");
            DATABASE = credentials.get("url") + "/" + credentials.get("database");
            PASSWORD = credentials.get("password");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found");
        }
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                USERNAME,
                PASSWORD
        );
    }
}
