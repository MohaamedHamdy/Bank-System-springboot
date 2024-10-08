package com.bank.app.bankNTI.dto;

import com.bank.app.bankNTI.entity.Account;
import lombok.Data;

@Data
public class TransactionDto {
    private String transactionType;
    private double amount;
    private long sourceAccountId;
    private long targetAccountId;
}
