package com.samadihadis.Banking.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samadihadis.Banking.controller.AccountController;
import com.samadihadis.Banking.dto.request.BalanceUpdateRequest;
import com.samadihadis.Banking.dto.request.CreateAccountRequest;
import com.samadihadis.Banking.dto.response.CreateAccountResponse;
import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.enums.AccountStatus;
import com.samadihadis.Banking.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void accountController_CreateAccount_ReturnAccount() throws Exception {


        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder()
                .accountNumber("1234567890")
                .shebaNumber("IR66557755")
                .balance(10000.0)
                .customerId(1L)
                .bankId(2L)
                .status(AccountStatus.OPEN)
                .build();

        CreateAccountResponse createAccountResponse = CreateAccountResponse.builder()
                .accountId(10L)
                .accountNumber("1234567890")
                .shebaNumber("IR66557755")
                .balance(10000.0)
                .customerId(1L)
                .bankId(2L)
                .status(AccountStatus.OPEN)
                .build();

        when(accountService.createAccount(any(Account.class), eq(1L), eq(2L)))
                .thenReturn(createAccountResponse);

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAccountRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(10L))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.customerId").value(1L))
                .andExpect(jsonPath("$.bankId").value(2L));

        verify(accountService, times(1))
                .createAccount(any(Account.class), eq(1L), eq(2L));
    }

    @Test
    public void accountController_GetAccountById_ReturnAccount() throws Exception{

        Account account = Account.builder()
                .accountId(1L)
                .accountNumber("1234567890")
                .shebaNumber("IR66557755")
                .balance(5000.0)
                .status(AccountStatus.OPEN)
                .build();

        when(accountService.getAccountById(eq(1L))).thenReturn(account);

        mockMvc.perform(get("/api/accounts/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.shebaNumber").value("IR66557755"))
                .andExpect(jsonPath("$.balance").value(5000.0))
                .andExpect(jsonPath("$.status").value("OPEN"));

        verify(accountService, times(1)).getAccountById(1L);


    }

    @Test
    public void accountController_GetAccountAccountNumber_ReturnAccount() throws Exception{

        Account account = Account.builder()
                .accountId(1L)
                .accountNumber("1234567890")
                .shebaNumber("IR66557755")
                .balance(5000.0)
                .status(AccountStatus.OPEN)
                .build();

        when(accountService.getAccountByAccountNumber("1234567890")).thenReturn(account);

        mockMvc.perform(get("/api/accounts//by-number/{accountNumber}", "1234567890")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.shebaNumber").value("IR66557755"))
                .andExpect(jsonPath("$.balance").value(5000.0))
                .andExpect(jsonPath("$.status").value("OPEN"));

        verify(accountService, times(1)).getAccountByAccountNumber("1234567890");


    }

    @Test
    public void accountController_UpdateBalance_ReturnNewBalance() throws Exception{

        Long accountId = 1L;
        Double newBalance = 2000000.0;

        BalanceUpdateRequest balanceUpdateRequest = new BalanceUpdateRequest();
        balanceUpdateRequest.setNewBalance(newBalance);

        Account updatedAccount = Account.builder()
                .accountId(accountId)
                .accountNumber("1234567890")
                .shebaNumber("IR66557755")
                .balance(newBalance)
                .status(AccountStatus.OPEN)
                .build();

        when(accountService.updateBalance(eq(accountId), eq(newBalance)))
                .thenReturn(updatedAccount);

        mockMvc.perform(put("/api/accounts/{id}/balance", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(balanceUpdateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.balance").value(newBalance))
                .andExpect(jsonPath("$.status").value("OPEN"));

        verify(accountService, times(1))
                .updateBalance(accountId, newBalance);
    }

    @Test
    public void accountController_updateBalance_return400() throws Exception {
        Long accountId = 1L;
        double newBalance = -100.0;

        BalanceUpdateRequest request = new BalanceUpdateRequest();
        request.setNewBalance(newBalance);

        when(accountService.updateBalance(eq(accountId), eq(newBalance)))
                .thenThrow(new RuntimeException("Balance cannot be negative"));

        mockMvc.perform(put("/api/accounts/{id}/balance", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(accountService, times(1))
                .updateBalance(accountId, newBalance);
    }


    @Test
    public void accountController_GetAllAccount_ReturnAllAccount() throws Exception{

        Account account1 = Account.builder()
                .accountId(1L)
                .accountNumber("1234567890")
                .shebaNumber("IR66557755")
                .balance(5000.0)
                .status(AccountStatus.OPEN)
                .build();

        Account account2 = Account.builder()
                .accountId(2L)
                .accountNumber("0987654321")
                .shebaNumber("IR6655007755")
                .balance(70000.0)
                .status(AccountStatus.OPEN)
                .build();

        List<Account> accounts = List.of(account1, account2);

        when(accountService.getAllAccounts()).thenReturn(accounts);

        mockMvc.perform(get("/api/accounts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].accountId").value(1))
                .andExpect(jsonPath("$[0].accountNumber").value("1234567890"))
                .andExpect(jsonPath("$[1].accountId").value(2))
                .andExpect(jsonPath("$[1].accountNumber").value("0987654321"));

        verify(accountService, times(1)).getAllAccounts();

    }

}
