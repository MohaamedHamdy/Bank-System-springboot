package com.bank.app.bankNTI.controller;

import com.bank.app.bankNTI.dto.TransactionDto;
import com.bank.app.bankNTI.entity.Transaction;
import com.bank.app.bankNTI.service.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionServices transactionServices;

    @Autowired
    public TransactionController(TransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }

    @PostMapping
    public Transaction makeTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionServices.makeTransaction(transactionDto);
    }

    @GetMapping("/{accountId}")
    public List<Transaction> getTransactions(@PathVariable long accountId) {
        System.out.println(accountId);
        return transactionServices.getTransactions(accountId);
    }
}
