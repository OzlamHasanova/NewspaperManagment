package com.example.newspapermanagment.exception;

public class NewspaperNotFoundException extends RuntimeException {
    public NewspaperNotFoundException(String message) {
        super(message);
    }
}
