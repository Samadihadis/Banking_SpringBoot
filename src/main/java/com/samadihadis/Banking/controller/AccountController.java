package com.samadihadis.Banking.controller;


import com.samadihadis.Banking.businessLogic.AccountService;
import com.samadihadis.Banking.dto.AccountRequest;
import com.samadihadis.Banking.dto.BalanceUpdateRequest;
import com.samadihadis.Banking.dto.StatusUpdateRequest;
import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.enums.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest){
        try {
            Account account = new Account();
            account.setAccountNumber(accountRequest.getAccountNumber());
            account.setShebaNumber(accountRequest.getShebaNumber());
            account.setBalance(accountRequest.getBalance());
            account.setStatus(accountRequest.getStatus() != null ? accountRequest.getStatus() : AccountStatus.OPEN);

            Account createdAccount = accountService.createAccount(account, accountRequest.getCustomerId(), accountRequest.getBankId());
            return ResponseEntity.ok(createdAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/by-number/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}/balance")
    public ResponseEntity<Account> updateBalance(@PathVariable Long id, @RequestBody BalanceUpdateRequest request) {
        try {
            Account updatedAccount = accountService.updateBalance(id, request.getNewBalance());
            return ResponseEntity.ok(updatedAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Account> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        try {
            Account updatedAccount = accountService.updateAccountStatus(id, request.getNewStatus());
            return ResponseEntity.ok(updatedAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok().build();
    }

}
