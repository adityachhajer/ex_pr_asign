package com.example.expense.tracker.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "user_dues")
@Data
public class UserDues {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dueId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long oweId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date repaymentDate;

    @Column(name = "isPaid", columnDefinition = "boolean default false")
    private boolean isPaid;
}
