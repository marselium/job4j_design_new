package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data/input.txt")) {
            StringBuilder builder = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                builder.append((char) read);
            }
            System.out.println(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
