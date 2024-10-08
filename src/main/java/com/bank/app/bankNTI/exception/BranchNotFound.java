package com.bank.app.bankNTI.exception;

public class BranchNotFound extends RuntimeException{
    public BranchNotFound() {
        super("Branch not found");
    }
}
