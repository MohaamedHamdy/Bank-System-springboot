package com.bank.app.bankNTI.controller;

import com.bank.app.bankNTI.dto.AccountDto;
import com.bank.app.bankNTI.dto.UpdateAccountDto;
import com.bank.app.bankNTI.entity.Account;
import com.bank.app.bankNTI.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity createAccount(@RequestBody AccountDto accountDto) {
        return accountService.createBranch(accountDto);
    }

    @GetMapping("/{accountId}")
    public Optional<Account> getAccountDetails(@PathVariable Long accountId) {
        return accountService.getAccountDetails(accountId);
    }

    @PutMapping("/update")
    public Account updateAccount(@RequestBody UpdateAccountDto updateAccountDto) {
        return accountService.updateAccountBalance(updateAccountDto);
    }
}
