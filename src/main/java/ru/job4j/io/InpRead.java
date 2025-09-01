package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class InpRead {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/inp.txt")){
            StringBuilder txt = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                txt.append((char) read);
            }
            String[] lines = txt.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
