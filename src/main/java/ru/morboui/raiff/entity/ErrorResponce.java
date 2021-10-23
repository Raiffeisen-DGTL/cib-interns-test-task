package ru.morboui.raiff.entity;


public class ErrorResponce {
    private final String code;
    private final String message;

    public ErrorResponce(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
