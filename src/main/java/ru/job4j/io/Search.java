package ru.job4j.io;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        Files.walkFileTree(start, new PrintFiles());
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}