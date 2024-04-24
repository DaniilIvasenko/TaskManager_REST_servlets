package ru.ivasenko.repository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
@Data
public class DbManager {
    private  final  String dbUrl;
    private  final String user;
    private  final  String password;


    /**
     * execute script from SQL file
     * @param pathToScriptFile path to file with script
     * @throws IOException if file is not found throw exception
     */
    public void executeScriptFromFile(String pathToScriptFile) throws IOException {
        String script = new String(Files.readAllBytes(Paths.get(pathToScriptFile)));
        executeScript(script);
    }


    /**
     * execute SQL script
     * @param SqlScript string with sql
     */
    public void executeScript(String SqlScript){
        try (Connection conn = DriverManager.getConnection(dbUrl, user, password);
             Statement stmt = conn.createStatement()) {
            for (String command : SqlScript.split(";")) {
                if (!command.trim().isEmpty()) {
                    stmt.executeUpdate(command + ";");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
