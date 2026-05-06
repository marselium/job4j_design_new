package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReader1 {
    public static void main(String[] args) {
        try (FileInputStream inp = new FileInputStream("data/input.txt")){
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = inp.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
