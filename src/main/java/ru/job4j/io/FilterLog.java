package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterLog {
    private final String file;

    public FilterLog(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader("data/log.txt"))){
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] arr = line.split(" ");
                if ("404".equals(arr[arr.length - 2])) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}
