package ru.project.raiffeisenbank.dto;

import java.util.List;

public class ErrorDTO {
    private List<String> messages;

    public ErrorDTO(String message) {
        this.messages = List.of(message);
    }

    public ErrorDTO(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessage() {
        return messages;
    }
}
