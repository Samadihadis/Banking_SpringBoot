package com.samadihadis.Banking.businessLogic;


import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.entity.Bank;
import com.samadihadis.Banking.entity.Customer;
import com.samadihadis.Banking.enums.AccountStatus;
import com.samadihadis.Banking.repository.AccountRepository;
import com.samadihadis.Banking.repository.BankRepository;
import com.samadihadis.Banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankRepository bankRepository;


    public Account createAccount(Account account, Long customerId, Long bankId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        account.setCustomer(customer);
        account.setBank(bank);

        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        return account.orElse(null);
    }

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

    public Account updateAccountStatus(Long accountId, AccountStatus accountStatus) {
        Account account = getAccountById(accountId);
        if (account != null) {
            account.setStatus(accountStatus);
            return accountRepository.save(account);
        }
        throw new RuntimeException("Account not found");
    }
}

