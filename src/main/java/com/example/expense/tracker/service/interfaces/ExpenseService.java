package com.example.expense.tracker.service.interfaces;


import com.example.expense.tracker.dto.TransactionDto;

public interface ExpenseService {

    public String addUserTransaction(TransactionDto transactions);
}
