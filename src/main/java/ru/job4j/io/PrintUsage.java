package ru.job4j.io;


import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"))) {
            stream.println("PrintStream to FileOutputStream");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
