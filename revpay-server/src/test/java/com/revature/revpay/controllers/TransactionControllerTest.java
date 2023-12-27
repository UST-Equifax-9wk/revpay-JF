package com.revature.revpay.controllers;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.revpay.entities.Transaction;
import com.revature.revpay.services.TransactionService;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllTransactions() throws Exception {
        // Arrange
        int accountNumber = 123;
        Set<Transaction> transactions = new HashSet<>();
        transactions.add(new Transaction(accountNumber, null, null, null, null, null, null));
        transactions.add(new Transaction(accountNumber, null, null, null, null, null, null));
        when(transactionService.findAllTransactionsByAccountId(accountNumber)).thenReturn(transactions);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/{accountNumber}", accountNumber))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(transactions)));
    }

    @Test
    public void testGetTransactionById() throws Exception {
        // Arrange
        int id = 1;
        Transaction transaction = new Transaction(id, null, null, null, null, null, null);
        when(transactionService.getTransactionById(id)).thenReturn(transaction);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(transaction)));
    }

    @Test
    public void testCreateTransaction() throws Exception {
        // Arrange
        Transaction transaction = new Transaction(null, null, null, null, null, null, null);
        when(transactionService.addTransaction(transaction)).thenReturn(transaction);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(transaction)));
    }

    @Test
    public void testUpdateTransaction() throws Exception {
        // Arrange
        Transaction transaction = new Transaction(null, null, null, null, null, null, null);
        when(transactionService.updateTransaction(transaction)).thenReturn(transaction);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(transaction)));
    }
}