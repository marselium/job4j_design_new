package ru.job4j.io.duplicates;

import ru.job4j.io.duplicates.DuplicatesVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        Map<FileProperty, List<Path>> res = visitor.getFilesByProperty();
        for (Map.Entry<FileProperty, List<Path>> entry : res.entrySet()) {
            if (entry.getValue().size() > 1) {
                List<Path> list = entry.getValue();
                System.out.println(entry.getKey().getName() + " - " + (double) (entry.getKey().getSize() / 1024) + " КБ");
                for (Path item : list) {
                    System.out.println(item.toAbsolutePath());
                }
            }
        }
    }
}
