package com.bank.app.bankNTI.exception;

public class AccountNotFound extends RuntimeException{
    public AccountNotFound() {
        super("Account not found");
    }
}
