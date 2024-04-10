package ru.ivasenko.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * статус выполнения задачи
 */
@Getter
public enum Status {
    TO_DO("в процессе"),
    COMPLETE("выполнена");

    private  String statusText;

    Status(String statusText) {
        this.statusText = statusText;
    }
}
