package ru.job4j.io;

import java.io.*;

public class Buff {
    public static void main(String[] args) {
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream("data/input.txt"));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data/output.txt"))) {
            out.write(stream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
