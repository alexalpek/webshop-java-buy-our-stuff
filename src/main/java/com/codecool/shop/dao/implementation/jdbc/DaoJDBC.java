package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.config.ConfigFileReader;
import lombok.Cleanup;

import java.sql.*;
import java.util.Map;

public abstract class DaoJDBC {

    private static final String DATABASE;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        ConfigFileReader cfr = new ConfigFileReader();
        Map<String, String> databaseData = null;

        try {
            databaseData = cfr.readDataFromFile("connection.properties");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        if (databaseData != null) {
            USERNAME = databaseData.get("user");
            DATABASE = databaseData.get("url") + databaseData.get("database");
            PASSWORD = databaseData.get("password");
        } else {
            USERNAME = System.getenv("username");
            DATABASE = System.getenv("database");
            PASSWORD = System.getenv("password");
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
