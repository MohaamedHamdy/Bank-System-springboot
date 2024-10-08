package com.bank.app.bankNTI.dto;

import lombok.Data;

@Data
public class UpdateAccountDto {
    private long accountId;
    private double amount;
    private String type;
}
