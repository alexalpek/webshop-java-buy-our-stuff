package com.codecool.shop.config;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseHandler {

    public Map<String, String> getDatabaseCredentials(String filename) throws ClassNotFoundException {
        ClassLoader cl = Class.forName("com.codecool.shop.config.DatabaseHandler").getClassLoader();
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

    public void executeCommandsFromFile(String commandsFileName, String credentialsFileName) {
        DatabaseHandler configFileReader = new DatabaseHandler();

        String cmd = configFileReader.getPsqlCommand(commandsFileName, credentialsFileName);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(".sql file not found");
        }
    }

    private String getPsqlCommand(String commandsFileName, String credentialsFileName) {
        URL res = getClass().getClassLoader().getResource(commandsFileName);

        try {
            if (res == null) {
                throw new FileNotFoundException();
            }
            File file = Paths.get(res.toURI()).toFile();
            String filePath = file.getAbsolutePath();

            Map<String, String> dbCredentials = getDatabaseCredentials(credentialsFileName);
            String user = dbCredentials.get("user");
            String database = dbCredentials.get("database");

            return "psql -U " + user + " -d " + database + " -f " + filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}