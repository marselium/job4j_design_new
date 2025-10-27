package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(
                new InputStreamReader(new FileInputStream(this.path), StandardCharsets.UTF_8))) {

            String line;
            int lineNumber = 0;

            while ((line = read.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Пропускаем пустые строки и комментарии
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                // Разделяем ключ и значение
                String[] parts = line.split("=", 2);
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid format on line " + lineNumber + ": " + line);
                }

                String key = parts[0].trim();
                String value = parts[1].trim();

                if (key.isEmpty()) {
                    throw new IllegalArgumentException("Empty key on line " + lineNumber + ": " + line);
                }

                values.put(key, value);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading config file: " + path, e);
        }
    }

    public String value(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        return value;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(
                new InputStreamReader(new FileInputStream(this.path), StandardCharsets.UTF_8))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("data/app.properties");
        config.load();
        System.out.println("Driver: " + config.value("hibernate.connection.driver_class"));
        System.out.println("URL: " + config.value("hibernate.connection.url"));
    }
}