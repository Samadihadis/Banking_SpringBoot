package com.samadihadis.Banking.controller;


import com.samadihadis.Banking.service.TransactionService;
import com.samadihadis.Banking.dto.request.DepositRequest;
import com.samadihadis.Banking.dto.request.TransferRequest;
import com.samadihadis.Banking.dto.request.WithdrawRequest;
import com.samadihadis.Banking.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransferRequest request) {
            Transaction transaction = transactionService.transfer(
                    request.getSourceAccountId(),
                    request.getDestinationAccountId(),
                    request.getAmount(),
                    request.getDescription()
            );
            return ResponseEntity.ok(transaction);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody DepositRequest request) {
            Transaction transaction = transactionService.deposit(
                    request.getAccountId(),
                    request.getAmount(),
                    request.getDescription()
            );
            return ResponseEntity.ok(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody WithdrawRequest request) {
            Transaction transaction = transactionService.withdraw(
                    request.getAccountId(),
                    request.getAmount(),
                    request.getDescription()
            );
            return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(
                transactionService.getTransactionById(id)
        );
    }
}

