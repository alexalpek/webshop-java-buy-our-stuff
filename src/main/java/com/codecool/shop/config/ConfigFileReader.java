package com.codecool.shop.config;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigFileReader {

    public ConfigFileReader() {
        try {
            readDataFromFile("connection.properties");
        }
        catch (ClassNotFoundException e) {
            System.err.println("Class not found");
        }
    }

    public static void main(String[] args) {
        ConfigFileReader read = new ConfigFileReader();
    }

    public List<String> readDataFromFile(String filename) throws ClassNotFoundException{

        StringBuilder sb = new StringBuilder();

        ClassLoader cl = Class.forName("com.codecool.shop.config.ConfigFileReader").getClassLoader();
        URL url = cl.getResource(filename);
        String file;

        if (url != null) {
            file = url.getFile();
        } else {
            return null;
        }

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.split(" ")[1]).append(" ");
            }
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        List<String> data = Arrays.asList(sb.toString().trim().split(" "));

        return data;
    }
}
