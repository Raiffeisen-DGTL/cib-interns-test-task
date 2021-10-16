package ru.raiffeisen.api.socks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum OperationEnum {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal"),
    GET,
    NO_CORRECT();

    @Getter
    private String value;

    public OperationEnum operationEnum (String operation) {
        if (operation.equals(MORE_THAN.value)) {
            return MORE_THAN;
        }
        else if (operation.equals(LESS_THAN.value)) {
            return LESS_THAN;
        }
        else if (operation.equals(EQUAL.value)) {
            return EQUAL;
        }
        else {
            return NO_CORRECT;
        }
    }
}
