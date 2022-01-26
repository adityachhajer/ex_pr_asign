package com.example.expense.tracker.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class UserDueDto {

    @Column(nullable = false)
    private String userEmailId;

    @Column(nullable = false)
    private String oweEmailId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Date repaymentDate;

}
