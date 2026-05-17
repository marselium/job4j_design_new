package ru.job4j.regex;

import java.io.*;

public class UsageEncoding {
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .map(s -> s + System.lineSeparator())
                    .forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))){
            writer.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./src/data/text.txt";
        UsageEncoding encoding = new UsageEncoding();
        String str = encoding.readFile(path);
        System.out.println("File data: ");
        System.out.println(str);
    }
}
