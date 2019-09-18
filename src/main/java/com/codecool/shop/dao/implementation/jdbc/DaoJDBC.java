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

        try {
            Map<String, String> databaseData = cfr.readDataFromFile("connection.properties");
            USERNAME = databaseData.get("user");
            DATABASE = databaseData.get("url") + "/" + databaseData.get("database");
            PASSWORD = databaseData.get("password");
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
