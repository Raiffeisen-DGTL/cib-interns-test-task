package api.dto;

import lombok.Getter;

@Getter
public class OperationDTO {

    private final String operation;

    public OperationDTO(String operation) {
        this.operation = operation;
    }
}

