package com.samadihadis.Banking.service;


import com.samadihadis.Banking.dto.response.CreateAccountResponse;
import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.entity.Bank;
import com.samadihadis.Banking.entity.Customer;
import com.samadihadis.Banking.enums.AccountStatus;
import com.samadihadis.Banking.repository.AccountRepository;
import com.samadihadis.Banking.repository.BankRepository;
import com.samadihadis.Banking.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {


    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final BankRepository bankRepository;


    public CreateAccountResponse createAccount(Account account, Long customerId, Long bankId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        account.setCustomer(customer);
        account.setBank(bank);

        Account saved = accountRepository.save(account);

        return CreateAccountResponse.builder()
                .accountId(saved.getAccountId())
                .accountNumber(saved.getAccountNumber())
                .shebaNumber(saved.getShebaNumber())
                .status(saved.getStatus())
                .balance(saved.getBalance())
                .customerId(saved.getCustomer().getCustomerId())
                .bankId(saved.getBank().getBankId())
                .build();
    }


    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    public Account getAccountByIdWithNotFoundDetection(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        return account.orElse(null);
    }

    @Transactional
    public Account updateBalance(Long accountId, Double newBalance) {
        Account account = getAccountById(accountId);
        if (account != null) {
            if (newBalance >= 0) {
                account.setBalance(newBalance);
                return accountRepository.save(account);
            } else {
                throw new RuntimeException("Balance cannot be negative");
            }
        }
        throw new RuntimeException("Account not found");
    }

    @Transactional
    public Account updateAccountStatus(Long accountId, AccountStatus accountStatus) {
        Account account = getAccountById(accountId);
        if (account != null) {
            account.setStatus(accountStatus);
            return accountRepository.save(account);
        }
        throw new RuntimeException("Account not found");
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}

