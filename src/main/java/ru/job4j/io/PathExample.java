package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) throws IOException {
        //Path dir = Paths.get("path/paths");
        //Files.createDirectories(dir);
        Path path = Path.of("path/paths");
        //Files.createFile(path);
        File file = path.toFile();
        System.out.println(file);
        file = path.getFileName().toFile();
        System.out.println(file);
    }
}
