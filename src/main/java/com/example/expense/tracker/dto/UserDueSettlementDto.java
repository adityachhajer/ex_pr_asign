package com.example.expense.tracker.dto;

import lombok.Data;

@Data
public class UserDueSettlementDto {

    private String userEmailId;

    private Double amount;
}
