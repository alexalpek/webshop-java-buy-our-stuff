package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.util.DatabaseHelper;

import java.sql.*;
import java.util.Map;

public abstract class DaoJDBC {

    private static final String DATABASE;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        String fileName = "sql/credentials/production_db.properties";

        try {
            Map<String, String> credentials = DatabaseHelper.getCredentials(fileName);
            USERNAME = credentials.get("user");
            DATABASE = credentials.get("url") + "/" + credentials.get("database");
            PASSWORD = credentials.get("password");
        }
        catch (ClassNotFoundException e){
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
