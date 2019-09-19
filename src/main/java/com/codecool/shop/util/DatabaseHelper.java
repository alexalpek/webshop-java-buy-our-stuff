package com.codecool.shop.util;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseHelper {

    public static void initDatabase() {
        String sqlFileName = "sql/sample_data.sql";
        String credentialsFileName = getCredentialsFileName();

        executeSqlFile(sqlFileName, credentialsFileName);
    }

    public static void clearDatabase() {
        String sqlFileName = "sql/init_db.sql";
        String credentialsFileName = getCredentialsFileName();

        executeSqlFile(sqlFileName, credentialsFileName);
    }

    public static Map<String, String> getCredentials(String filename) throws ClassNotFoundException {
        ClassLoader cl = Class.forName("com.codecool.shop.util.DatabaseHelper").getClassLoader();
        Map<String, String> databaseData = new HashMap<>();

        try {
            InputStream input = cl.getResourceAsStream(filename);

            Properties prop = new Properties();

            if (input != null) {
                prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            }

            databaseData.put("url", prop.getProperty("url"));
            databaseData.put("database", prop.getProperty("database"));
            databaseData.put("user", prop.getProperty("user"));
            databaseData.put("password", prop.getProperty("password"));
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return databaseData;
    }

    private static String getCredentialsFileName() {
        if (Util.isJUnitTest()) {
            return "sql/credentials/test_db.properties";
        } else {
            return "sql/credentials/production_db.properties";
        }
    }

    private static void executeSqlFile(String sqlFileName, String credentialsFileName) {
        String cmd = getPsqlCommand(sqlFileName, credentialsFileName);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        } catch (InterruptedException e) {
            throw new RuntimeException("Process interrupter");
        }
    }

    private static String getPsqlCommand(String commandsFileName, String credentialsFileName) {
        URL res = DatabaseHelper.class.getClassLoader().getResource(commandsFileName);

        try {
            if (res == null) {
                throw new FileNotFoundException();
            }
            File file = Paths.get(res.toURI()).toFile();
            String filePath = file.getAbsolutePath();

            Map<String, String> dbCredentials = getCredentials(credentialsFileName);
            String user = dbCredentials.get("user");
            String database = dbCredentials.get("database");

            return "psql -U " + user + " -d " + database + " -f " + filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}