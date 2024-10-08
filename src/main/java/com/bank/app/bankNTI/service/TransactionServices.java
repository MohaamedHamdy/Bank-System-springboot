package com.bank.app.bankNTI.service;

import com.bank.app.bankNTI.dto.TransactionDto;
import com.bank.app.bankNTI.entity.Account;
import com.bank.app.bankNTI.entity.Transaction;
import com.bank.app.bankNTI.exception.AccountNotFound;
import com.bank.app.bankNTI.exception.insufficientBalance;
import com.bank.app.bankNTI.repository.AccountRepository;
import com.bank.app.bankNTI.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionServices {
    private TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionServices(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction makeTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        long sourceAccId = transactionDto.getSourceAccountId();
        long targetAccId = transactionDto.getTargetAccountId();
        if(sourceAccId > 0 && targetAccId > 0 && sourceAccId != targetAccId) {
            Optional<Account> sourceAccountOpt = accountRepository.findById(sourceAccId);
            Optional<Account> targetAccountOpt = accountRepository.findById(targetAccId);
            if (sourceAccountOpt.isPresent() && targetAccountOpt.isPresent()) {
                Account sourceAccount = sourceAccountOpt.get();
                if(sourceAccount.getBalance() >= transactionDto.getAmount()){
                    sourceAccount.setBalance(sourceAccount.getBalance() - transactionDto.getAmount());
                } else {
                    throw new insufficientBalance();
                }
                Account targetAccount = targetAccountOpt.get();
                targetAccount.setBalance(targetAccount.getBalance() + transactionDto.getAmount());
                transaction.setSourceAccount(sourceAccount);
                transaction.setTargetAccount(targetAccount);
            }
        }else{
            throw new AccountNotFound();
        }
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactions(long accountId) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);

        if (accountOpt.isPresent()) {
            List<Transaction> accountList = new ArrayList<>();
            Account account = accountOpt.get();

            List<Transaction> sourceTransactions = account.getSourceTransactions();
            List<Transaction> targetTransactions = account.getTargetTransactions();

            accountList.addAll(sourceTransactions);
            accountList.addAll(targetTransactions);

            return accountList;
        }  else {
            throw new AccountNotFound();
        }
    }



}
