package ru.danilarassokhin.raiffeisensocks.dto;

public class ResponseDto<O> {

    private String message;
    private O data;

    public ResponseDto(String message) {
        this.message = message;
        this.data = null;
    }

    public ResponseDto(O data) {
        this.data = data;
        this.message = "";
    }

    public ResponseDto(String message, O data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public O getData() {
        return data;
    }

    public void setData(O data) {
        this.data = data;
    }
}
