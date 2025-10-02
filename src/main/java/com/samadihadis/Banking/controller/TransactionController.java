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
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransferRequest request) {
        try {
            Transaction transaction = transactionService.transfer(
                    request.getSourceAccountId(),
                    request.getDestinationAccountId(),
                    request.getAmount(),
                    request.getDescription()
            );
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody DepositRequest request) {
        try {
            Transaction transaction = transactionService.deposit(
                    request.getAccountId(),
                    request.getAmount(),
                    request.getDescription()
            );
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody WithdrawRequest request) {
        try {
            Transaction transaction = transactionService.withdraw(
                    request.getAccountId(),
                    request.getAmount(),
                    request.getDescription()
            );
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        }
        return ResponseEntity.notFound().build();
    }
}




