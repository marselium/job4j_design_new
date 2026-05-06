package ru.job4j.io;

import javax.imageio.IIOException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResFile {
    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("data/dataresult.txt")){
            outputStream.write("Hello, World!".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
