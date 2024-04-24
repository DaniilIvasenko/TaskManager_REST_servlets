package ru.ivasenko.repository;

import ru.ivasenko.data.Task;

import java.util.List;

/**
 * The interface Task repository.
 */
public interface iTaskRepository {
    /**
     * Crete new task.
     *
     * @param newTask the new task
     * @return the task
     */
    Task creteTask(Task newTask);

    /**
     * Find all tasks by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Task> findAllTasksByUserId(Long userId);

    /**
     * Update task by id.
     *
     * @param UpdatedTask the updated task
     * @param taskId      the task id
     */
    void updateTaskById(Task UpdatedTask, Long taskId);

    /**
     * Delete task by id.
     *
     * @param id the id
     */
    void deleteTaskById(Long id);
}
