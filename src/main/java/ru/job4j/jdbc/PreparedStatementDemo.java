package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementDemo {

    private Connection connection;

    public PreparedStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        Config config = new Config("data/app.properties");
        config.load();
        Class.forName(config.value("jdbc.driver"));
        String url = config.value("jdbc.url");
        String login = config.value("jdbc.username");
        String password = config.value("jdbc.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void insert(City city) {
        try (PreparedStatement statement =
                connection.prepareStatement("INSERT INTO cities(name, population) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(City city) {
        boolean rsl = false;
        try (PreparedStatement statement =
                connection.prepareStatement("UPDATE cities SET name = ?, population = ? WHERE id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            rsl = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public void delete(int id) {
        try (PreparedStatement statement =
                connection.prepareStatement("DELETE FROM cities WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM cities")) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }
}
