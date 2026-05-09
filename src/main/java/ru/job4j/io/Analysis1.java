package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Analysis1 {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            String line;
            boolean flag = true;
            String sep = System.lineSeparator();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (flag == (data[0].contains("500") || data[0].contains("400"))) {
                    out.append(data[1]).append(";").append(flag ? "" : sep);
                    flag = !flag;
                }
            }
            } catch(IOException e){
                e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis1 analysis = new Analysis1();
        String source = "data/server.log";
        String target = "data/target.txt";
        analysis.unavailable(source, target);
    }
}
