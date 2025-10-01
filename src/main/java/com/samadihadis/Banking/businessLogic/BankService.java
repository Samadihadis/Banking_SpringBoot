package com.samadihadis.Banking.businessLogic;

import com.samadihadis.Banking.entity.Bank;
import com.samadihadis.Banking.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {

    private BankRepository bankRepository;

    public Bank createBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank getBankById(Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        return bank.orElse(null);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }
}