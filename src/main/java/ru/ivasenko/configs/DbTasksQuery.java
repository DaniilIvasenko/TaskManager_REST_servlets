package ru.ivasenko.configs;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DbTasksQuery  extends DbQueryConfig{
    private final String tablePrefix = ".tasks";

    public DbTasksQuery() {
        super();
        super.setPrefix(super.getPrefix()+tablePrefix);
    }


    /**
     * get query from properties file (new task creation)
     * INSERT INTO task_manager.tasks (title, text, status, dateofcreation, deadline, user_id) VALUES (?, ?, ?, ?, ?);
     * @return sql query string;
     */
    public  String creteTask(){
        return super.getProperties().get(super.getPrefix()+".createTask").toString();
    }

    /**
     * get query from properties file (search all tasks by user id)
     * SELECT * FROM task_manager.task WHERE user_id = ?;
     * @return sql query string;
     */
    public String findAllTasksByUserId(){
        return super.getProperties().get(super.getPrefix()+".findAllTasksByUserId").toString();
    }


    /**
     * get query from properties file (update task by id)
     * UPDATE task_manager.tasks SET title = ?, text = ?, status = ?, deadline = ? WHERE id = ?;
     * @return sql query string;
     */
    public  String updateTaskById(){
        return super.getProperties().get(super.getPrefix()+".updateTaskById").toString();
    }


    /**
     * get query from properties file (update task by id)
     * DELETE FROM task_manager.tasks WHERE id = ?;
     * @return sql query string;
     */
    public String deleteTaskById(){
        return super.getProperties().get(super.getPrefix()+".deleteTaskById").toString();
    }
}
