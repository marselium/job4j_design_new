package ru.job4j.io;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter1 {
    private final String file;

    public LogFilter1(String file) {
        this.file = file;
    }

    public List<String> filter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().filter(s -> s.indexOf(" 404 ") >= s.length() - 10
            ).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public void saveTo(String out) {
        var data = filter();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {
            for (int i = 0; i < data.size(); i++) {
                writer.write(data.get(i));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter1("data/log.txt").saveTo("data/log404.txt");

    }
}
