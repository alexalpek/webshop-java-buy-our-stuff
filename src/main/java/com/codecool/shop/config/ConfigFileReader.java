package com.codecool.shop.config;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigFileReader {

    public Map<String, String> readDataFromFile(String filename) throws ClassNotFoundException{

        ClassLoader cl = Class.forName("com.codecool.shop.config.ConfigFileReader").getClassLoader();
        Map<String, String> databaseData = new HashMap<>();

        try {
            InputStream input = cl.getResourceAsStream(filename);

            Properties prop = new Properties();

            if (input != null) {
                prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
            }

            databaseData.put("url", prop.getProperty("url"));
            databaseData.put("database", prop.getProperty("database"));
            databaseData.put("user", prop.getProperty("user"));
            databaseData.put("password", prop.getProperty("password"));
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return databaseData;
    }
}
