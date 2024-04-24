package ru.ivasenko.configs;

/**
 * The type Db user data query.
 */
public class DbUserDataQuery extends DbQueryConfig{
    private final String tablePrefix = ".userData";

    /**
     * Instantiates a new Db user data query.
     */
    public DbUserDataQuery() {
        super();
        super.setPrefix(super.getPrefix()+tablePrefix);
    }

    /**
     * Add user data string.
     * INSERT INTO task_manager.user_data (login, password, user_id) VALUES (?, ?, ?);
     * @return the string
     */
    public  String addUserData(){
        return  super.getProperties().get(super.getPrefix()+".addUserData").toString();
    }


    /**
     * Get user data by user id string.
     * SELECT * FROM task_manager.user_data WHERE user_id = ?;
     * @return the string
     */
    public  String getUserDataByUserId(){
        return  super.getProperties().get(super.getPrefix()+".getUserDataByUserId").toString();
    }
}
