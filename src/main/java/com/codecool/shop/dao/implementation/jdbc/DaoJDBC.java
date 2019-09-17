package com.codecool.shop.dao.implementation.jdbc;

import java.sql.*;

public abstract class DaoJDBC {

    private static final String DATABASE;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        USERNAME = System.getenv("username");
        DATABASE = System.getenv("database");
        PASSWORD = System.getenv("password");
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                USERNAME,
                PASSWORD
        );
    }
}
