package com.example.expense.tracker.dto;

import com.example.expense.tracker.models.enums.Categories;
import com.example.expense.tracker.models.enums.ModeOfPayment;
import com.example.expense.tracker.models.enums.TransactionType;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {

    private String emailId;

    private TransactionType transactionType;

    private ModeOfPayment modeOfPayment;

    private Date transactionDate;

    private Categories categories;

    private String description;

    private Double amount;

    private String currencyType;

}
