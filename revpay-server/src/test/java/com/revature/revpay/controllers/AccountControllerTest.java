package com.revature.revpay.controllers;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.revature.revpay.entities.Account;
import com.revature.revpay.services.AccountService;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        // Mock AccountService methods
        Account account = new Account();
        account.setId(1);
        account.setAccountNumber("1234567890");
        account.setAccountHolderName("John Doe");
        account.setBalance(1000.0);

        when(accountService.findAccountById(1)).thenReturn(account);
        when(accountService.addAccount(account)).thenReturn(account);
        when(accountService.updateAccount(account)).thenReturn(account);
        when(accountService.deleteAccount(1)).thenReturn(true);
    }

    @Test
    public void testGetAccountById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountHolderName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000.0));
    }

    @Test
    public void testCreateAccount() throws Exception {
        Account account = new Account();
        account.setAccountNumber("1234567890");
        account.setAccountHolderName("John Doe");
        account.setBalance(1000.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(account.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountHolderName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000.0));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account = new Account();
        account.setId(1);
        account.setAccountNumber("1234567890");
        account.setAccountHolderName("John Doe");
        account.setBalance(1000.0);

        mockMvc.perform(MockMvcRequestBuilders.put("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(account.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountHolderName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000.0));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the deleteAccount method was called
        verify(accountService, times(1)).deleteAccount(1);
    }
}