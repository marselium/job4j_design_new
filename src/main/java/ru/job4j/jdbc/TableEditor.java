package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Config config = new Config("data/app.properties");
        config.load();
        Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        this.connection = DriverManager.getConnection(url, login, password); // добавить this.connection =
    }

    public void executeSql(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s",
                tableName,
                "id SERIAL PRIMARY KEY"
        );
        executeSql(sql);
    }


    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "DROP TABLE %s", tableName
        );
        executeSql(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s",
                tableName,
                columnName,
                type
        );
        executeSql(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s",
                tableName,
                columnName
        );
        executeSql(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName,
                columnName,
                newColumnName);
        executeSql(sql);
    }

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream("data/app.properties")) {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TableEditor table = new TableEditor(prop);

        table.createTable("testTable()");
        System.out.println(table.getTableScheme("testTable"));

        table.addColumn("testTable", "ColInt", "integer");
        System.out.println(table.getTableScheme("testTable"));

        table.addColumn("testTable", "ColText", "text");
        System.out.println(table.getTableScheme("testTable"));

        table.dropColumn("testTable", "ColText");
        System.out.println(table.getTableScheme("testTable"));

        table.renameColumn("testTable", "ColInt", "ColIntegers");
        System.out.println(table.getTableScheme("testTable"));

        table.dropTable("testTable");

    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}