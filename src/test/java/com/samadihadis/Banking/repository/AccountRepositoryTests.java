package com.samadihadis.Banking.repository;


import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.enums.AccountStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.flyway.enabled=false"
})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void AccountRepository_SaveAll_ReturnSaveAccount(){

        Account account = Account.builder()
                .accountNumber("121212")
                .shebaNumber("66557755")
                .balance(2000000000.0)
                .status(AccountStatus.OPEN).build();


        Account savedAccount = accountRepository.save(account);

        Assertions.assertThat(savedAccount).isNotNull();
        Assertions.assertThat(savedAccount.getAccountId()).isGreaterThan(0L);

    }

    @Test
    public void AccountRepository_GetAll_ReturnMoreThanAllAccount(){

        Account account1 = Account.builder()
                .accountNumber("121212")
                .shebaNumber("66557755")
                .balance(2000000000.0)
                .status(AccountStatus.OPEN).build();

        Account account2 = Account.builder()
                .accountNumber("343434")
                .shebaNumber("9900886767")
                .balance(3000000000.0)
                .status(AccountStatus.OPEN).build();


        accountRepository.save(account1);
        accountRepository.save(account2);

        List<Account> accountList = accountRepository.findAll();

        Assertions.assertThat(accountList).isNotNull();
        Assertions.assertThat(accountList.size()).isEqualTo(2);

    }

    @Test
    public void AccountRepository_FindById_ReturnAccount(){

        Account account1 = Account.builder()
                .accountNumber("121212")
                .shebaNumber("66557755")
                .balance(2000000000.0)
                .status(AccountStatus.OPEN).build();

        accountRepository.save(account1);

        Account accountList = accountRepository.findById(account1.getAccountId()).get();

        Assertions.assertThat(accountList).isNotNull();

    }

    @Test
    public void AccountRepository_FindByAccountNumber_ReturnAccountNumber(){

        Account account1 = Account.builder()
                .accountNumber("121212")
                .shebaNumber("66557755")
                .balance(2000000000.0)
                .status(AccountStatus.OPEN).build();

        accountRepository.save(account1);

        Account accountList = accountRepository.findByAccountNumber(account1.getAccountNumber()).get();

        Assertions.assertThat(accountList).isNotNull();

    }

}
