package ru.ivasenko.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.ivasenko.data.User;

@Data
@RequiredArgsConstructor
public class UserDataDTO {
    private  String login;
    private  String password;
}
