package ru.ivasenko;

import ru.ivasenko.configs.DbUsersQuery;
import ru.ivasenko.data.Task;
import ru.ivasenko.data.User;
import ru.ivasenko.dto.TaskDTO;
import ru.ivasenko.dto.UserDTO;
import ru.ivasenko.mapper.ListTaskMapper;
import ru.ivasenko.mapper.TaskMapper;
import ru.ivasenko.mapper.UserMapper;
import ru.ivasenko.repository.DbManager;
import ru.ivasenko.repository.UserRepository;
import ru.ivasenko.service.UserService;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DbUsersQuery dbUsersQuery = new DbUsersQuery();
        DbManager manager = new DbManager("jdbc:postgresql://localhost:5432/TaskManager", "postgres", "postgres");
        try {
            manager.executeScriptFromFile("src/main/resources/TaskManagerInit.sql");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
