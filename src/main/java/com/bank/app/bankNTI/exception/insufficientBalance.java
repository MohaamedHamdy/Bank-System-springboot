package com.bank.app.bankNTI.exception;

public class insufficientBalance extends RuntimeException{
    public insufficientBalance() {
        super("insufficient Balance");
    }
}
