package ru.ivasenko.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ivasenko.data.User;
import ru.ivasenko.data.UserData;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserDataRepositoryTest {
    UserDataRepository userDataRepository;
    String testDbInitialisation = "src/main/resources/TaskManagerTestInit.sql";
    DbManager dbManager;

    @BeforeEach
    void setUp() {
        userDataRepository = new UserDataRepository();
        String DbUrl = userDataRepository.getProperties().getProperty("db.url");
        String DbUser = userDataRepository.getProperties().getProperty("db.user");
        String DbPassword = userDataRepository.getProperties().getProperty("db.password");

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
        userDataRepository = null;
    }

    @Test
    void addUserData() {
        UserRepository userRepository = new UserRepository();
        User newUser = userRepository.createUser(new User("Ivan", "ivan@Mail.ru"));

        UserData userData = new UserData();
        userData.setUser(newUser);
        userData.setLogin("test login");
        userData.setPassword("test password");
        System.out.println(userData.getId());

        userData = userDataRepository.addUserData(userData);
        System.out.println(userData.getId());

    }

    @Test
    void getUserDataByUserId() {
        UserRepository userRepository = new UserRepository();
        User newUser = userRepository.createUser(new User("Ivan", "ivan@Mail.ru"));

        UserData userData = new UserData();
        userData.setUser(newUser);
        userData.setLogin("test login");
        userData.setPassword("test password");
        userData = userDataRepository.addUserData(userData);

        UserData dataFromDb = userDataRepository.getUserDataByUserId(userData.getUser().getId());
        System.out.println(userData);
        System.out.println(dataFromDb);

    }
}