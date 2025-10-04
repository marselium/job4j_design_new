package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileResult {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileWriter("data/dataresult.txt"))) {
            out.println("Hello World!");
            out.printf("%s%n", "Test printf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
