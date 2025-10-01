package com.samadihadis.Banking.controller;

import com.samadihadis.Banking.businessLogic.AccountService;
import com.samadihadis.Banking.dto.request.BalanceUpdateRequest;
import com.samadihadis.Banking.dto.request.CreateAccountRequest;
import com.samadihadis.Banking.dto.request.StatusUpdateRequest;
import com.samadihadis.Banking.dto.response.CreateAccountResponse;
import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.enums.AccountStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody @Validated CreateAccountRequest accountRequest) {
        try {
            Account account = new Account();
            account.setAccountNumber(accountRequest.getAccountNumber());
            account.setShebaNumber(accountRequest.getShebaNumber());
            account.setBalance(accountRequest.getBalance());
            account.setStatus(accountRequest.getStatus() != null ? accountRequest.getStatus() : AccountStatus.OPEN);

            var createAccountResponse = accountService.createAccount(account, accountRequest.getCustomerId(), accountRequest.getBankId());
            return ResponseEntity.ok(createAccountResponse);
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
