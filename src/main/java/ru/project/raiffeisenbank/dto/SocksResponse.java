package ru.project.raiffeisenbank.dto;

public class SocksResponse<T> {
    private T result;

    private ErrorDTO error;

    public SocksResponse(T result) {
        this(result, null);
    }

    public SocksResponse(ErrorDTO error) {
        this(null, error);
    }

    public SocksResponse(T result, ErrorDTO error) {
        this.result = result;
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }
}
