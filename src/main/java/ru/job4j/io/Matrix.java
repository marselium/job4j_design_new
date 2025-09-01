package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/matrix.txt")){
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    output.write(Integer.toString(i * j ).getBytes());
                    output.write(" ".getBytes());
                }
                output.write("; \n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
