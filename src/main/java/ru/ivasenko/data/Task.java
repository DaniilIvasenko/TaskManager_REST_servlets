package ru.ivasenko.data;

import lombok.*;

import java.time.LocalDateTime;

/**
 * сущность задачи
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    private Long id;
    private String title;
    private String text;
    private Status status;
    private LocalDateTime dateOfCreation;
    private LocalDateTime deadLine;
    private User user;

}
