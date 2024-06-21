package com.example.newspapermanagment.exception;

public class InvalidDateFormatException extends RuntimeException {
    public InvalidDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
