package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(target)) {
                String line;
                boolean flag = true;
                while ((line = input.readLine()) != null) {
                    String[] data = line.split(" ");
                    if (flag == ("400".equals(data[0]) || "500".equals(data[0]))) {
                        output.append(data[1]).append("; ").append(flag ? "" : System.lineSeparator());
                        flag = !flag;
                    }
                }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}

