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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

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
                .status(saved.getAccountStatus())
                .balance(saved.getBalance())
                .customerId(saved.getCustomer().getCustomerId())
                .bankId(saved.getBank().getBankId())
                .build();
    }


    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Transactional
    public Account updateBalance(Long accountId, BigDecimal newBalance) {
        Account account = getAccountById(accountId);

            if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
                account.setBalance(newBalance);
                return accountRepository.save(account);
            } else {
                throw new RuntimeException("Balance cannot be negative");
            }
    }

    @Transactional
    public Account updateAccountStatus(Long accountId, AccountStatus accountStatus) {
        Account account = getAccountById(accountId);
        if (account != null) {
            account.setAccountStatus(accountStatus);
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

    public List<Account> getAccountByCustomerId(Long customerId){
        return accountRepository.findByCustomerId(customerId);
    }

    public List<Account> getAccountByBankId(Long bankId){
        return accountRepository.findByBankId(bankId);
    }

    public List<Account> getAccountByStatus(AccountStatus accountStatus){
        return accountRepository.findByAccountStatus(accountStatus);
    }

}

