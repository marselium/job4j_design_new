package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AbuseTest {

    @Test
    void drop(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("Hi foolish dude");
            writer.println("Java job4j php");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish", "php"));

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))){
            reader.lines().forEach(builder::append);
        }
        assertThat("Hi dude Java job4j ").hasToString(builder.toString());
    }
}