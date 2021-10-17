package tz.example.project.exceptions;

public class InvalidParametrException extends RuntimeException{
    public InvalidParametrException(String message) {
        super(message);
    }
}
