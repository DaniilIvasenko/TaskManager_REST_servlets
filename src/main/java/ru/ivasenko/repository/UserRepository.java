package ru.ivasenko.repository;

import lombok.Getter;
import ru.ivasenko.configs.DbUsersQuery;
import ru.ivasenko.data.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

@Getter
public class UserRepository extends CustomRepository implements iUserRepository{
    private DbUsersQuery dbUsersQuery;

    public UserRepository() {
        super();
        dbUsersQuery =  new DbUsersQuery();
    }

    @Override
    public User createUser(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(dbUsersQuery.createUser(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            int affected = preparedStatement.executeUpdate();
            if (affected == 1){
                ResultSet keys = preparedStatement.getGeneratedKeys();
                keys.next();
                Long newKey = keys.getLong(1);
                user.setId(newKey);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User findUserById(Long id) {
        User result = new User();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(dbUsersQuery.findUserById());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.setName(resultSet.getString("name"));
                result.setEmail(resultSet.getString("email"));
                result.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public User updateUserById(User updatedUser, Long id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(dbUsersQuery.updateUserById());
            preparedStatement.setString(1, updatedUser.getName());
            preparedStatement.setString(2, updatedUser.getEmail());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedUser;
    }

    @Override
    public void deleteUserById(Long id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(dbUsersQuery.deleteUserById());
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
