package ru.ivasenko.data;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private  String name;
    private String email;
    private UserData userData;
    private List<Task> tasks;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
