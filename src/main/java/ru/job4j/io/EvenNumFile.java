package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data/eveneven.txt")){
            StringBuilder builder = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                builder.append((char) read);
            }
            String[] lines = builder.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
