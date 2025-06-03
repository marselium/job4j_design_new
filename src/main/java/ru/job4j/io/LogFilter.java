package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] arr = line.split(" ");
                if ("404".equals(arr[arr.length - 2])) {
                    list.add(line);
                    list.add("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            data.forEach(output::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        System.out.println(logFilter.filter());
        logFilter.saveTo("data/out.txt");
    }
}