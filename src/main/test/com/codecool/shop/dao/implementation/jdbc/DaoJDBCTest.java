package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.config.DatabaseHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public class DaoJDBCTest {

    @BeforeEach
    void setupDatabase() {
        DatabaseHandler configFileReader = new DatabaseHandler();
        String credentialsFileName = "sql/credentials/test_db.properties";
        configFileReader.executeCommandsFromFile("sql/init_db.sql", credentialsFileName);
        configFileReader.executeCommandsFromFile("sql/sample_data.sql", credentialsFileName);
    }

    @AfterAll
    static void cleanDatabase() {
        DatabaseHandler configFileReader = new DatabaseHandler();
        String credentialsFileName = "sql/credentials/test_db.properties";
        configFileReader.executeCommandsFromFile("sql/init_db.sql", credentialsFileName);
    }
}
