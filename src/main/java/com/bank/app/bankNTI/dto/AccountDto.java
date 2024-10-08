package com.bank.app.bankNTI.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class AccountDto {
    @NotBlank(message = "Account Number can't be empty")
    private String accountNumber;
    @NotBlank(message = "Balance can't be empty")
    private double balance;
    @NotBlank(message = "Account type can't be empty")
    private String accountType;
    @NotBlank(message = "User id can't be empty")
    private long userId;
}
