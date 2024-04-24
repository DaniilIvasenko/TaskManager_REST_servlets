package ru.ivasenko.repository;

import lombok.Getter;
import ru.ivasenko.configs.DbQueryConfig;
import ru.ivasenko.configs.DbTasksQuery;
import ru.ivasenko.configs.DbUsersQuery;
import ru.ivasenko.data.Status;
import ru.ivasenko.data.Task;
import ru.ivasenko.data.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Getter
public class TaskRepository extends CustomRepository implements iTaskRepository {
    /**
     * config with sql query
     */
    protected DbTasksQuery dbTasksQuery;
    public TaskRepository() {
        super();
        dbTasksQuery = new DbTasksQuery();
    }


    @Override
    public Task creteTask(Task newTask) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbTasksQuery.creteTask(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, newTask.getTitle());
            preparedStatement.setString(2, newTask.getText());
            preparedStatement.setString(3, newTask.getStatus().getStatusText());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(newTask.getDateOfCreation()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(newTask.getDeadLine()));
            preparedStatement.setLong(6, newTask.getUser().getId());

            int affected = preparedStatement.executeUpdate();
            if (affected == 1){
                ResultSet keys = preparedStatement.getGeneratedKeys();
                keys.next();
                Long newKey = keys.getLong(1);
                newTask.setId(newKey);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newTask;
    }


    @Override
    public List<Task> findAllTasksByUserId(Long userId) {
        List<Task> result = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(dbTasksQuery.findAllTasksByUserId())) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Task nextTask =  new Task();
                nextTask.setId(resultSet.getLong("id"));
                nextTask.setTitle(resultSet.getString("title"));
                nextTask.setText(resultSet.getString("text"));
                nextTask.setStatus(Status.getStatusByString(resultSet.getString("status")));
                nextTask.setDateOfCreation(resultSet.getTimestamp("dateOfCreation").toLocalDateTime());
                nextTask.setDeadLine(resultSet.getTimestamp("deadLine").toLocalDateTime());
                User mockUser = new User();
                mockUser.setId(resultSet.getLong("user_id"));
                nextTask.setUser(mockUser);

                result.add(nextTask);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public void updateTaskById(Task updatedTask, Long taskId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbTasksQuery.updateTaskById())) {

            preparedStatement.setString(1, updatedTask.getTitle());
            preparedStatement.setString(2, updatedTask.getText());
            preparedStatement.setString(3, updatedTask.getStatus().getStatusText());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(updatedTask.getDeadLine()));
            preparedStatement.setLong(5, taskId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteTaskById(Long id) {
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(dbTasksQuery.deleteTaskById())) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
