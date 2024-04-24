package ru.ivasenko.configs;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DbUsersQuery extends DbQueryConfig{

    private final String tablePrefix =".users";


    public DbUsersQuery() {
        super();
        super.setPrefix(super.getPrefix()+tablePrefix);
    }


    /**
     * get query new user creation from properties file
     * INSERT INTO task_manager.users (name, email) VALUE (?, ?);
     * @return query string
     */
    public String createUser(){
        String query = super.getProperties().get(super.getPrefix()+".create_user").toString();
        return query;
    }


    /**
     * get query find user by id from properties file
     * SELECT * FROM task_manager.users WHERE id = ?;
     * @return query string
     */
    public String findUserById(){
        String query = super.getProperties().get(super.getPrefix()+".find_user_by_id").toString();
        return query;
    }


    /**
     * get query update user by id from properties file
     * UPDATE task_manager.users SET name = ?, email = ? WHERE id = ?;
     * @return query string
     */
    public  String updateUserById(){
        String query = super.getProperties().get(super.getPrefix()+".update_user_by_id").toString();
        return query;
    }


    /**
     * get query update user by id from properties file
     * DELETE FROM task_manager.users WHERE id = ?;
     * @return query string
     */
    public  String deleteUserById(){
        String query = super.getProperties().get(super.getPrefix()+".delete_user_by_id").toString();
        return query;
    }



}
