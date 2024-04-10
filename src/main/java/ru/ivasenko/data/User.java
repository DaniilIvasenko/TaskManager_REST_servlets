package ru.ivasenko.data;

import lombok.Data;

import java.util.List;
@Data
public class User {
    private Long id;
    private  String name;
    private String email;
    private UserData userData;
    private List<Task> tasks;
}
