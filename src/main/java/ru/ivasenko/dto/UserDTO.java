package ru.ivasenko.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ivasenko.data.Task;
import ru.ivasenko.data.UserData;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {
    private  String name;
    private String email;
    private UserData userData;
    private List<TaskDTO> tasks;
}
