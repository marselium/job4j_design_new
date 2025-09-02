package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintUse {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/prnt.txt"))){
            stream.println("PrintStream to FilterOutputStream");
            stream.write("new line using write .getBytes".getBytes());
            PrintWriter writer = new PrintWriter("data/write");
            writer.println("New message");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
