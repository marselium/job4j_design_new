package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(str -> {
                String[] nameMail = str.split(";", 2);
                if (nameMail[0].isEmpty() || nameMail[1].isEmpty()) {
                    throw new IllegalArgumentException("Your string must be like: \"name;email;\"");
                }
                users.add(new User(nameMail[0], nameMail[1].replace(";", "")));
            });
        }
        return users;
    }


    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO usersImport(name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = new FileInputStream("db/app.properties")) { // или другой путь
            properties.load(in);
        }
        ImportDB db = new ImportDB(properties, "db/dump.txt");
        db.save(db.load());
    }
}