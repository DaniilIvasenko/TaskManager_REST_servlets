package ru.ivasenko.data;

import lombok.*;

import java.time.LocalDateTime;

/**
 * сущность задачи
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String text;
    private Status status;
    private LocalDateTime dateOfCreation;
    private LocalDateTime deadLine;
    private User user;

    public Task(String title, String text, Status status, LocalDateTime dateOfCreation, LocalDateTime deadLine, User user) {
        this.title = title;
        this.text = text;
        this.status = status;
        this.dateOfCreation = dateOfCreation;
        this.deadLine = deadLine;
        this.user = user;
    }
}
