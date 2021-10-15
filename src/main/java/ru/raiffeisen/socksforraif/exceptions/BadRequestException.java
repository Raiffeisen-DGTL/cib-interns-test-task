package ru.raiffeisen.socksforraif.exceptions;

public class BadRequestException extends RuntimeException {
        public BadRequestException(String message){
            super(message);
        }
}
