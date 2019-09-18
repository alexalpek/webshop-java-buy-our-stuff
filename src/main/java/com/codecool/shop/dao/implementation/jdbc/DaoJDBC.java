package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.config.DatabaseHandler;

import java.sql.*;
import java.util.Map;

public abstract class DaoJDBC {

    private static final String DATABASE;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        DatabaseHandler cfr = new DatabaseHandler();
        String fileName = "sql/credentials/production_db.properties";

        try {
            Map<String, String> databaseData = cfr.getDatabaseCredentials(fileName);
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
