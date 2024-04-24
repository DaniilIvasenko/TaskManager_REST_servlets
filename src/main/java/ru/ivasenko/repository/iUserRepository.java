package ru.ivasenko.repository;

import ru.ivasenko.data.User;

/**
 * The interface User repository.
 */
public interface iUserRepository {

    /**
     * Create new user.
     *
     * @param user the user
     * @return the user from DB after creation
     */
    User createUser(User user);

    /**
     * Find user by id user.
     *
     * @param id the id
     * @return the user
     */
    User findUserById(Long id);

    /**
     * Update user by id user.
     *
     * @param updatedUser the updated user
     * @param id          users id
     * @return the user
     */
    User updateUserById(User updatedUser, Long id);

    /**
     * Delete user by id.
     *
     * @param id the id
     */
    void  deleteUserById(Long id);
}
