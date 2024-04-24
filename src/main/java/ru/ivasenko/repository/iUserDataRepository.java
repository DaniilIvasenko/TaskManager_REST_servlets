package ru.ivasenko.repository;

import ru.ivasenko.data.UserData;

/**
 * The interface User data repository.
 */
public interface iUserDataRepository {
    /**
     * Add user data.
     *
     * @param userData the user data
     * @return the user data with id
     */
    UserData addUserData(UserData userData);


    /**
     * Gets user data by user id.
     *
     * @param UserId the user id
     * @return the user data by user id.
     */
    UserData getUserDataByUserId(Long UserId);
}
