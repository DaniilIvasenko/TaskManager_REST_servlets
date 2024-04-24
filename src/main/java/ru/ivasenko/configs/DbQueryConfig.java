package ru.ivasenko.configs;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
@Data
public class DbQueryConfig {
    private Properties properties;
    private  final  String propertiesFile = "src/main/resources/DbQuery.properties";
    private  String prefix = "db.query";

    public DbQueryConfig() {
        properties = new Properties();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get(propertiesFile))) {
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException("DbQueryConfig -> propertiesFile is not exists");
        }
    }
}
