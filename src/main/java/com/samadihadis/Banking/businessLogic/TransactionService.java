package com.samadihadis.Banking.businessLogic;

import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.entity.Transaction;
import com.samadihadis.Banking.services.account.types.AccountStatus;
import com.samadihadis.Banking.enums.TransactionStatus;
import com.samadihadis.Banking.repository.AccountRepository;
import com.samadihadis.Banking.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    @Transactional
    public Transaction transfer(Long sourceAccountId, Long destinationAccountId, Double amount, String description) {

        Account sourceAccount = accountRepository.findById(sourceAccountId)
                .orElseThrow(() -> new RuntimeException("حساب مبدا یافت نشد"));

        Account destinationAccount = accountRepository.findById(destinationAccountId)
                .orElseThrow(() -> new RuntimeException("حساب مقصد یافت نشد"));

        validateTransfer(sourceAccount, destinationAccount, amount);

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setTransactionAmount(amount);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionDescription(description);

        return transactionRepository.save(transaction);
    }


    private void validateTransfer(Account sourceAccount, Account destinationAccount, Double amount) {
        if (sourceAccount.getStatus() != AccountStatus.OPEN) {
            throw new RuntimeException("حساب مبدا بسته است");
        }
        if (destinationAccount.getStatus() != AccountStatus.OPEN) {
            throw new RuntimeException("حساب مقصد بسته است");
        }

        if (sourceAccount.getBalance() < amount) {
            throw new RuntimeException("موجودی کافی نیست");
        }

        if (amount <= 0) {
            throw new RuntimeException("مبلغ انتقال باید مثبت باشد");
        }
    }


    public Transaction deposit(Long accountId, Double amount, String description) {
        Account destinationAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("حساب مقصد یافت نشد"));

        if (destinationAccount.getStatus() != AccountStatus.OPEN) {
            throw new RuntimeException("حساب مقصد بسته است");
        }

        if (amount <= 0) {
            throw new RuntimeException("مبلغ واریز باید مثبت باشد");
        }

        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
        accountRepository.save(destinationAccount);

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(null);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setTransactionAmount(amount);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionDescription(description);

        return transactionRepository.save(transaction);

    }

    public Transaction withdraw(Long accountId, Double amount, String description) {

        Account sourceAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("حساب مبدا یافت نشد"));

        if (sourceAccount.getStatus() != AccountStatus.OPEN) {
            throw new RuntimeException("حساب مبدا بسته است");
        }

        if (amount <= 0) {
            throw new RuntimeException("مبلغ برداشت باید مثبت باشد");
        }

        if (sourceAccount.getBalance() < amount) {
            throw new RuntimeException("موجودی کافی نیست");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        accountRepository.save(sourceAccount);

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(null);
        transaction.setTransactionAmount(amount);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionDescription(description);

        return transactionRepository.save(transaction);
    }

    public Transaction updateTransactionStatus(Long transactionId, TransactionStatus transactionStatus) {
        Transaction transaction = getTransactionById(transactionId);
        if (transaction != null) {
            transaction.setTransactionStatus(transactionStatus);
            return transactionRepository.save(transaction);
        }
        throw new RuntimeException("Account not found");
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

}
