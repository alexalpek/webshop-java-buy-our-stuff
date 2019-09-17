package com.codecool.shop.config;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConfigFileReader {

    public Map<String, String> readDataFromFile(String filename) throws ClassNotFoundException{

        ClassLoader cl = Class.forName("com.codecool.shop.config.ConfigFileReader").getClassLoader();
        URL url = cl.getResource(filename);
        String file;

        if (url != null) {
            file = url.getFile();
        } else {
            return null;
        }

        Map<String, String> databaseData = new HashMap<>();

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(": ");
                databaseData.put(lineData[0], lineData[1]);
            }
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return databaseData;
    }
}
