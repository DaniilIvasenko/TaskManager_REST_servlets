package ru.ivasenko.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ivasenko.data.Status;
import ru.ivasenko.data.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String text;
    private Status status;
    private LocalDateTime dateOfCreation;
    private LocalDateTime deadLine;
    private Long userID;
}
