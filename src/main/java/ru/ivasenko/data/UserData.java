package ru.ivasenko.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private  Long id;
    private  User user;
    private  String login;
    private  String password;

}
