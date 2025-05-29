package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("data/Multiplication.txt")) {
            for (int i = 1; i <= 10; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 1; j <= 10; j++) {
                    stringBuilder.append(i * j).append("; ");
                }
                outputStream.write(stringBuilder.toString().getBytes());
                outputStream.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
