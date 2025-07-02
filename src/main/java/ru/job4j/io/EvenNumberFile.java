package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data/even.txt")) {
            StringBuilder builder = new StringBuilder();
            int rsl;
            while ((rsl = inputStream.read()) != -1) {
                builder.append((char) rsl);
            }
            String[] lines = builder.toString().split(System.lineSeparator());
            for (String line: lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
