package com.bank.app.bankNTI.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super("User not found");
    }
}
