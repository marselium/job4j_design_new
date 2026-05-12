package ru.job4j.io;

import java.io.File;
import java.text.Format;

public class Dir1 {
    public static void main(String[] args) {
        File file = new File("c:\\\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("Размер директории: %s", file.getTotalSpace()));
        String sep = System.lineSeparator();
        for (File subfile : file.listFiles()) {
            System.out.printf("File name is : %s ,  size is: %d " + sep, subfile.getName() , subfile.length());
        }
    }
}
