package ru.ivasenko.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

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


    public static  Status  getStatusByString(String status){
        Status enumStatus = Arrays.stream(Status.values())
                .filter(x->x.getStatusText().equals(status))
                .findFirst().orElseThrow(RuntimeException::new);
        return enumStatus;
    }
}
