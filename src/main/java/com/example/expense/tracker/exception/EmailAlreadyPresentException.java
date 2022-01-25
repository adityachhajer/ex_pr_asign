package com.example.expense.tracker.exception;

public class EmailAlreadyPresentException extends RuntimeException{

    public EmailAlreadyPresentException(String s) {
        super(s);
    }
}
