package com.example.expense.tracker.exception;

public class InternalServerErrorException extends RuntimeException{

    private static final long serialVersionUID = 2L;

    public InternalServerErrorException(String s) {
        super(s);
    }
}
