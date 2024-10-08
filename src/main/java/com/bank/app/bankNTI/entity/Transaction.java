package com.bank.app.bankNTI.entity;

import com.bank.app.bankNTI.enums.TransactionTypes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

//    @Enumerated(EnumType.STRING)
    private String transactionType;

    private double amount;
    @CreationTimestamp
    private LocalDateTime timestamp;

    //source acc
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_account_id")
    @JsonManagedReference
    private Account sourceAccount;
    //target acc
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "target_account_id")
    private Account targetAccount;
}
