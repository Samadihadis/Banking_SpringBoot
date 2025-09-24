package com.samadihadis.Banking.controller;

import com.samadihadis.Banking.businessLogic.BankService;
import com.samadihadis.Banking.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        try {
            Bank createdBank = bankService.createBank(bank);
            return ResponseEntity.ok(createdBank);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBank(@PathVariable Long id) {
        Bank bank = bankService.getBankById(id);
        if (bank != null) {
            return ResponseEntity.ok(bank);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return ResponseEntity.ok(banks);
    }
}
