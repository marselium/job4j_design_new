package ru.job4j.io;

import java.io.*;

public class BufferedStreams {
    public static void main(String[] args) {
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream("data/input.txt"));
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("data/out.txt", true))) {
                output.write(input.readAllBytes());
                output.write("New line".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
