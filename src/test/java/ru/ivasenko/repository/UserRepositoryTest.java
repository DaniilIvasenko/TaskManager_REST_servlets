package ru.ivasenko.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ivasenko.data.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    UserRepository userRepository;
    String testDbInitialisation = "src/main/resources/TaskManagerTestInit.sql";
    DbManager dbManager;

    public UserRepositoryTest() {
    }

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        String DbUrl = userRepository.getProperties().getProperty("db.url");
        String DbUser = userRepository.getProperties().getProperty("db.user");
        String DbPassword = userRepository.getProperties().getProperty("db.password");

        dbManager = new DbManager(DbUrl, DbUser, DbPassword);
        try {
            dbManager.executeScriptFromFile(testDbInitialisation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        dbManager.executeScript("DROP SCHEMA task_manager CASCADE");
        userRepository = null;
    }

    @Test
    void createUser() {
        User newUser = userRepository.createUser(new User("Ivan", "ivan@Mail.ru"));
        //todo доделать тесты
    }

    @Test
    void findUserById() {
        User user  = userRepository.findUserById(1l);
        //todo доделать тесты
    }

    @Test
    void updateUserById() {
        User userFromDb = userRepository.findUserById(1l);
        System.out.println(userFromDb);
        User userAfterUpdate = userRepository.updateUserById(new User("test user","test_user@mail.ru"), 1l);
        System.out.println(userAfterUpdate);
        System.out.println(userRepository.findUserById(1l));
        //todo доделать тесты
    }

    @Test
    void deleteUserById() {
        User newUser = userRepository.createUser(new User("Ivan", "ivan@Mail.ru"));
        System.out.println(userRepository.findUserById(1l));
        userRepository.deleteUserById(1l);
        System.out.println(userRepository.findUserById(1l));
        //todo доделать тесты
    }
}