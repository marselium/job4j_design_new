package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data/even.txt")) {
            int read;
            StringBuilder stringBuilder = new StringBuilder();
            while ((read = inputStream.read()) != -1) {
                    stringBuilder.append((char) read);
            }
            String[] strings = stringBuilder.toString().split(System.lineSeparator());
            for (String str : strings) {
                if (Integer.parseInt(str) % 2 == 0) {
                    System.out.println(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
