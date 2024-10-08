package com.bank.app.bankNTI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    private String accountNumber;
    private double balance;
    private String accountType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    // list of transactions
    @OneToMany(mappedBy = "sourceAccount", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Transaction> sourceTransactions;
    @OneToMany(mappedBy = "targetAccount", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Transaction> targetTransactions;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JsonBackReference
//    private List<Transaction> transactions;
}
