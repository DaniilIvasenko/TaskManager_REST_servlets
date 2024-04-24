CREATE SCHEMA IF NOT EXISTS task_manager;

CREATE TABLE IF NOT EXISTS task_manager.users (
    id bigserial primary key NOT NULL ,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS task_manager.user_data (
    id bigserial NOT NULL PRIMARY KEY  ,
    login VARCHAR(45) NOT NULL,
    password VARCHAR(200) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES task_manager.users (id)  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS task_manager.tasks (
    id bigserial primary key NOT NULL ,
    title VARCHAR(100) NOT NULL,
    text VARCHAR(500),
    status VARCHAR(50) NOT NULL,
    dateOfCreation TIMESTAMP  NOT NULL,
    deadLine TIMESTAMP,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES task_manager.users (id)  ON DELETE CASCADE
);

INSERT INTO task_manager.users(name, email)
VALUES ('USER 1', 'USER_1@mail.ru'),
    ('USER 2', 'USER_2@mail.ru');

INSERT INTO task_manager.user_data (login, password, user_id)
VALUES ('login user1', 'password user 1', 1),
    ('login user2', 'password user 2', 2);

INSERT INTO task_manager.tasks(title, text, status, dateOfCreation, deadLine, user_id)
VALUES ('Task №1 user 1', 'task 1 text', 'выполнена', '2024-5-10 11:30:30', '2024-5-20 00:00:00',1),
    ('Task №1 user 2', 'task 2 text', 'в процессе', '2024-5-15 10:30:30', '2024-5-20 00:00:00',2),
    ('Task №2 user 1', 'task 3 text', 'в процессе', '2024-5-10 11:30:30', '2024-5-20 00:00:00',1),
    ('Task №2 user 2', 'task 4 text', 'выполнена', '2024-5-10 11:30:30', '2024-5-20 00:00:00',1);


