package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"))) {
            stream.println("Из PrintStream в FileOutputStream");
            stream.write("New line with using \"write\"".getBytes());
            PrintWriter writer = new PrintWriter("data/print.txt");
            writer.println("New line with using \"PrintWriter\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
