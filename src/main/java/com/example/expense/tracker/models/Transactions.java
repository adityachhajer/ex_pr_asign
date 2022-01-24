package com.example.expense.tracker.models;

import com.example.expense.tracker.models.enums.Categories;
import com.example.expense.tracker.models.enums.ModeOfPayment;
import com.example.expense.tracker.models.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private ModeOfPayment modeOfPayment;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @Column(nullable = false)
    private Categories categories;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currencyType;

}
