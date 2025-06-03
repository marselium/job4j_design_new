package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileWriter(target))) {
            boolean flag = true;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] arr = line.split(" ");
                if (flag == (arr[0].contains("400") || arr[0].contains("500"))) {
                    builder.append(arr[arr.length - 1]).append("; ").append(flag ? "" : System.lineSeparator());
                    flag = !flag;
                }
            }
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}