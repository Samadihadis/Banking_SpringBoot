package com.samadihadis.Banking.controller;

import com.samadihadis.Banking.service.AccountService;
import com.samadihadis.Banking.dto.request.BalanceUpdateRequest;
import com.samadihadis.Banking.dto.request.CreateAccountRequest;
import com.samadihadis.Banking.dto.request.StatusUpdateRequest;
import com.samadihadis.Banking.dto.response.CreateAccountResponse;
import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.enums.AccountStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest accountRequest) {

            Account account = new Account();
            account.setAccountNumber(accountRequest.getAccountNumber());
            account.setShebaNumber(accountRequest.getShebaNumber());
            account.setBalance(accountRequest.getBalance());
            account.setAccountStatus(accountRequest.getStatus() != null ? accountRequest.getStatus() : AccountStatus.OPEN);

            var createAccountResponse = accountService.createAccount(account, accountRequest.getCustomerId(), accountRequest.getBankId());
            return ResponseEntity.ok(createAccountResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/by-number/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByAccountNumber(accountNumber));
    }

    @PutMapping("/{id}/balance")
    public ResponseEntity<Account> updateBalance(@PathVariable Long id, @RequestBody BalanceUpdateRequest request) {
            Account updatedAccount = accountService.updateBalance(id, request.getNewBalance());
            return ResponseEntity.ok(updatedAccount);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Account> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
            Account updatedAccount = accountService.updateAccountStatus(id, request.getNewStatus());
            return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}
