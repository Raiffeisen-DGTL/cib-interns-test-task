package ru.akkulov.raiffeisen.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс для ответа при невалидных входных данных.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SockIncorrectDataObject {

    private String info;
}
