package com.codecool.shop;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Read {

    public Read() {
        readDataFromFile("connection.properties");
    }

    public static void main(String[] args) {
        Read read = new Read();
    }

    public List<String> readDataFromFile(String filename) {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.split(" ")[1]).append(" ");
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        List<String> data = Arrays.asList(sb.toString().trim().split(" "));

        return data;
    }
}
