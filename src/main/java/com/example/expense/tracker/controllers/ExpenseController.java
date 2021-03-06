package com.example.expense.tracker.controllers;

import com.example.expense.tracker.config.Constants;
import com.example.expense.tracker.dto.TransactionDto;
import com.example.expense.tracker.service.interfaces.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> addTransactions(@RequestBody TransactionDto transaction){
        LOGGER.info("Inside {} to add the transactions",this.getClass());

        expenseService.addUserTransaction(transaction);
        return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }
}
