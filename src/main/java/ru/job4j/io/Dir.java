package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Dir %s is not exists ", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Wrong %s is not exists ", file.getAbsoluteFile()));
        }
        System.out.println(file.getTotalSpace());
        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getName());
            System.out.println(subfile.getAbsoluteFile());
            System.out.println(String.format("file size is %s bytes", subfile.length()));
            System.out.println("------");
        }
    }
}
