package com.bank.app.bankNTI.service;


import com.bank.app.bankNTI.dto.AccountDto;
import com.bank.app.bankNTI.dto.UpdateAccountDto;
import com.bank.app.bankNTI.entity.Account;
import com.bank.app.bankNTI.entity.User;
import com.bank.app.bankNTI.exception.AccountNotFound;
import com.bank.app.bankNTI.exception.UserNotFound;
import com.bank.app.bankNTI.exception.insufficientBalance;
import com.bank.app.bankNTI.repository.AccountRepository;
import com.bank.app.bankNTI.repository.UserRepository;
import com.bank.app.bankNTI.success.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<SuccessResponse> createBranch(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBalance(accountDto.getBalance());
        long userId = accountDto.getUserId();
        if (userId > 0) {
            Optional<User> userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                account.setUser(user);
            } else {
                throw new UserNotFound();
            }
        }
        accountRepository.save(account);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Account created successfully");
        successResponse.setCode(HttpStatus.CREATED.toString());

        return ResponseEntity.ok(successResponse);
    }

    public Optional<Account> getAccountDetails(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public Account updateAccountBalance(UpdateAccountDto updateAccountDto) {
        long accountId = updateAccountDto.getAccountId();
        String type = updateAccountDto.getType();
        double amount = updateAccountDto.getAmount();
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFound::new);
        System.out.println(account.getBalance());

        if (Objects.equals(type, "deposit")) {
            double balance = account.getBalance() + amount;
            account.setBalance(balance);
            accountRepository.save(account);

        } else if (Objects.equals(type, "withdraw")) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            } else {
                throw new insufficientBalance();
            }
        }
        return account;
    }


}
