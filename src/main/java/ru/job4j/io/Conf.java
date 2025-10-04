package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Conf {

    private final String path;
    private final Map values = new HashMap();

    public Conf(final String path) {
        this.path = path;
    }

    public void load() {
        String line;
        try (BufferedReader input = new BufferedReader(new FileReader(this.path))) {
            while ((line = input.readLine()) != null) {
                if(!line.isEmpty() && !line.startsWith("#")) {
                    String[] val = line.split("=", 2);
                    if (val.length < 2 || val[0].isEmpty() || val[1].isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    values.put(val[0], val[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        //throw new UnsupportedOperationException("Don't impl this method yet!");
        return (String) values.get(key);
    }


    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
