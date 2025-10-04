package ru.job4j.io;

import java.io.*;
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

    public void saveTo(String out) {
        var data = filter();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(out))){
            for (String dat : data) {
                writer.write(dat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        logFilter.saveTo("data/outFile.txt");
    }
}
