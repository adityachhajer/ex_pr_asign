package com.example.expense.tracker.exception;

public class InternalServerError extends RuntimeException{

    private static final long serialVersionUID = 2L;

    public InternalServerError(String s) {
        super(s);
    }
}
