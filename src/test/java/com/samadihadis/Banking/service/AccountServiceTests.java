package com.samadihadis.Banking.service;


import com.samadihadis.Banking.dto.request.AccountRequest;
import com.samadihadis.Banking.dto.request.CreateAccountRequest;
import com.samadihadis.Banking.dto.response.CreateAccountResponse;
import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.entity.Bank;
import com.samadihadis.Banking.entity.Customer;
import com.samadihadis.Banking.enums.AccountStatus;
import com.samadihadis.Banking.repository.AccountRepository;
import com.samadihadis.Banking.repository.BankRepository;
import com.samadihadis.Banking.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void AccountService_CreateAccount_ReturnCreateAccount(){
        Long customerId = 3L;
        Long bankId = 1L;

        Account account = Account.builder()
                .accountNumber("121212")
                .shebaNumber("89898989")
                .balance(20_000_000.0)
                .status(AccountStatus.OPEN)
                .build();

        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        Bank bank = new Bank();
        bank.setBankId(bankId);

        Account savedAccount = Account.builder()
                .accountId(10L)
                .accountNumber("121212")
                .shebaNumber("89898989")
                .balance(20_000_000.0)
                .status(AccountStatus.OPEN)
                .customer(customer)
                .bank(bank)
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        CreateAccountResponse createAccountResponse = accountService.createAccount(account, customerId, bankId);

        Assertions.assertThat(createAccountResponse).isNotNull();
        Assertions.assertThat(createAccountResponse.getAccountId()).isEqualTo(10L);
        Assertions.assertThat(createAccountResponse.getAccountNumber()).isEqualTo("121212");
        Assertions.assertThat(createAccountResponse.getShebaNumber()).isEqualTo("89898989");
        Assertions.assertThat(createAccountResponse.getStatus()).isEqualTo(AccountStatus.OPEN);
        Assertions.assertThat(createAccountResponse.getCustomerId()).isEqualTo(customerId);
        Assertions.assertThat(createAccountResponse.getBankId()).isEqualTo(bankId);

    }

    @Test
    public void AccountService_GetAccountById_ReturnAccountById(){
        Long customerId = 3L;
        Long bankId = 1L;

        Account account = Account.builder()
                .accountNumber("121212")
                .shebaNumber("89898989")
                .balance(20_000_000.0)
                .status(AccountStatus.OPEN)
                .build();

        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        Bank bank = new Bank();
        bank.setBankId(bankId);

        Account savedAccount = Account.builder()
                .accountId(10L)
                .accountNumber("121212")
                .shebaNumber("89898989")
                .balance(20_000_000.0)
                .status(AccountStatus.OPEN)
                .customer(customer)
                .bank(bank)
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));
        when(accountRepository.save(Mockito.any(Account.class))).thenReturn(savedAccount);

        CreateAccountResponse createAccountResponse = accountService.createAccount(account, customerId, bankId);

        Assertions.assertThat(createAccountResponse).isNotNull();

    }

    @Test
    public void AccountService_GetUpdateBalance_ReturnBalance(){
        Long accountId = 3L;
        Double newBalance = 5000.0;

        Account account = Account.builder()
                .accountNumber("121212")
                .shebaNumber("89898989")
                .balance(20_000_000.0)
                .status(AccountStatus.OPEN)
                .build();


        Account updatedAccount = Account.builder()
                .accountId(10L)
                .accountNumber("121212")
                .balance(5000.0)
                .status(AccountStatus.OPEN)
                .build();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountRepository.save(Mockito.any(Account.class))).thenReturn(updatedAccount);

        Account result = accountService.updateBalance(accountId, newBalance);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getBalance()).isEqualTo(newBalance);

    }

    @Test
    public void AccountService_GetUpdateAccountStatus_ReturnAccountStatus(){
        Long accountId = 3L;
        AccountStatus newStatus = AccountStatus.CLOSE;

        Account account = Account.builder()
                .accountNumber("121212")
                .shebaNumber("89898989")
                .balance(20_000_000.0)
                .status(AccountStatus.OPEN)
                .build();


        Account updatedStatus = Account.builder()
                .accountId(10L)
                .accountNumber("121212")
                .status(AccountStatus.CLOSE)
                .build();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountRepository.save(Mockito.any(Account.class))).thenReturn(updatedStatus);

        Account result = accountService.updateAccountStatus(accountId, newStatus);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getStatus()).isEqualTo(newStatus);

    }

    @Test
    public void AccountService_GetAllAccount_ReturnAllAccount(){

        Account account1 = Account.builder()
                .accountNumber("121212")
                .shebaNumber("89898989")
                .balance(20_000_000.0)
                .status(AccountStatus.OPEN)
                .build();

        Account account2 = Account.builder()
                .accountNumber("343434")
                .shebaNumber("67676767")
                .balance(70_000_000.0)
                .status(AccountStatus.OPEN)
                .build();

        List<Account> accountList = List.of(account1, account2);

        when(accountRepository.findAll()).thenReturn(accountList);

        List<Account> result = accountService.getAllAccounts();

        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void AccountService_DeleteAccount_ShouldCallRepositoryOnce() {
        Long accountId = 5L;

        accountService.deleteAccount(accountId);

        verify(accountRepository, times(1)).deleteById(accountId);
        verifyNoMoreInteractions(accountRepository);
    }

}
