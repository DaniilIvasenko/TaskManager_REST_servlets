package ru.ivasenko.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ivasenko.data.Status;
import ru.ivasenko.data.Task;
import ru.ivasenko.data.User;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

class TaskRepositoryTest {

    TaskRepository taskRepository;
    String testDbInitialisation = "src/main/resources/TaskManagerTestInit.sql";
    DbManager dbManager;

    public TaskRepositoryTest() {
    }

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepository();
        String DbUrl = taskRepository.getProperties().getProperty("db.url");
        String DbUser = taskRepository.getProperties().getProperty("db.user");
        String DbPassword = taskRepository.getProperties().getProperty("db.password");

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
        taskRepository = null;
    }

    @Test
    void creteTask() {
        User testUser = new User("Ivan", "ivan@Mail.ru");
        testUser.setId(1l);
        Task newTask = taskRepository.creteTask(new Task("title",
                "text",
                Status.TO_DO,
                LocalDateTime.now(),
                LocalDateTime.of(2024, 5, 26, 12, 0),
                testUser));
        Task taskAfterSave = taskRepository.creteTask(newTask);
        //todo сделать тесты
    }

    @Test
    void findAllTasksByUserId() {
        List<?> task = taskRepository.findAllTasksByUserId(1l);
        for (Object o : task) {
            System.out.println(o);
        }

    }

    @Test
    void updateTaskById() {
        User testUser = new User("Ivan", "ivan@Mail.ru");
        testUser.setId(2l);
        Task newTask = taskRepository.creteTask(new Task("title",
                "text",
                Status.TO_DO,
                LocalDateTime.now(),
                LocalDateTime.of(2024, 5, 26, 12, 0),
                testUser));
        taskRepository.findAllTasksByUserId(2l).stream().forEach(System.out::println);
        System.out.println();

        newTask.setText("updatedText");
        newTask.setDeadLine(LocalDateTime.now());
        taskRepository.updateTaskById(newTask, newTask.getId());

        taskRepository.findAllTasksByUserId(2l).stream().forEach(System.out::println);
        //todo сделать тесты
    }

    @Test
    void deleteTaskById() {
        User testUser = new User("Ivan", "ivan@Mail.ru");
        testUser.setId(2l);
        Task newTask = taskRepository.creteTask(new Task("title",
                "text",
                Status.TO_DO,
                LocalDateTime.now(),
                LocalDateTime.of(2024, 5, 26, 12, 0),
                testUser));
        taskRepository.findAllTasksByUserId(2l).stream().forEach(System.out::println);
        System.out.println();

        taskRepository.deleteTaskById(2l);
        taskRepository.findAllTasksByUserId(2l).stream().forEach(System.out::println);
        //todo сделать тесты
    }
}