package ru.ivasenko.repository;

import lombok.Data;
import ru.ivasenko.configs.DbQueryConfig;
import ru.ivasenko.configs.DbTasksQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
@Data
public class CustomRepository {
    private Properties properties;
    private final Path propertiesFilePath = Paths.get("src/main/resources/DB.properties");


    public CustomRepository() {
        properties = new Properties();
        try (BufferedReader fileReader = Files.newBufferedReader(propertiesFilePath)) {
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException("TaskRepository -> propertiesFile is not exists");
        }
    }

    /**
     * get connection to current DB
     * @return connection
     * @throws SQLException
     */
    protected Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        Connection connection;
        connection = DriverManager.getConnection(url, user, password);
        return  connection;
    }

}
