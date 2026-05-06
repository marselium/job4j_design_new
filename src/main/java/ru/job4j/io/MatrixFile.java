package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MatrixFile {
    public static void main(String[] args) {
        try (OutputStream out = new FileOutputStream("data/MatrixFile.txt")){
            int n = 0;
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    n = i * j;
                    out.write(Integer.toString(n).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
