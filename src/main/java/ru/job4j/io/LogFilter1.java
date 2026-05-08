package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
           return reader.lines().filter(s -> s.indexOf(" 404 ") >= 1
           ).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public static void main(String[] args) {
        LogFilter1 logFilter = new LogFilter1("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
