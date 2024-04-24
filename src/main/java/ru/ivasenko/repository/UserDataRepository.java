package ru.ivasenko.repository;

import ru.ivasenko.configs.DbUserDataQuery;
import ru.ivasenko.data.User;
import ru.ivasenko.data.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataRepository extends CustomRepository implements iUserDataRepository{

    private DbUserDataQuery dbUserDataQuery;

    public UserDataRepository() {
        this.dbUserDataQuery = new DbUserDataQuery();
    }

    @Override
    public UserData addUserData(UserData userData) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(dbUserDataQuery.addUserData(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userData.getLogin());
            statement.setString(2, userData.getPassword());
            statement.setLong(3, userData.getUser().getId());

            int affected = statement.executeUpdate();
            if (affected == 1){
                ResultSet resultSet= statement.getGeneratedKeys();
                resultSet.next();
                userData.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userData;
    }

    @Override
    public UserData getUserDataByUserId(Long UserId) {
        UserData result = new UserData();
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(dbUserDataQuery.getUserDataByUserId())) {
            statement.setLong(1, UserId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.setId(resultSet.getLong("id"));
                result.setLogin(resultSet.getString("login"));
                result.setPassword(resultSet.getString("password"));
                User mockUser = new User();
                mockUser.setId(resultSet.getLong("user_id"));
                result.setUser(mockUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
