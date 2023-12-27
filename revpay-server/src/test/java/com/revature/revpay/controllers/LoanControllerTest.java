package com.revature.revpay.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.revature.revpay.entities.Loan;
import com.revature.revpay.services.LoanService;

@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    private List<Loan> loans;

    @BeforeEach
    public void setUp() {
        loans = new ArrayList<>();
        Loan loan1 = new Loan();
        loan1.setId(1);
        loan1.setAccountId(1);
        loan1.setAmount(1000.0);
        loans.add(loan1);

        Loan loan2 = new Loan();
        loan2.setId(2);
        loan2.setAccountId(1);
        loan2.setAmount(2000.0);
        loans.add(loan2);

        when(loanService.findAllLoansByAccount_id(1)).thenReturn(loans);
        when(loanService.findLoanById(1)).thenReturn(loan1);
        when(loanService.findLoanById(2)).thenReturn(loan2);
        when(loanService.addLoan(any(Loan.class))).thenReturn(loan1);
        when(loanService.updateLoan(any(Loan.class))).thenReturn(loan1);
        when(loanService.deleteLoan(1)).thenReturn(true);
        when(loanService.deleteLoan(2)).thenReturn(false);
    }

    @Test
    public void testGetAllLoansByAccountId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].accountId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(1000.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].accountId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].amount").value(2000.0));
    }

    @Test
    public void testGetLoanById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(1000.0));
    }

    @Test
    public void testGetLoanById_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateLoan() throws Exception {
        Loan loan = new Loan();
        loan.setAccountId(1);
        loan.setAmount(1000.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loan.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(1000.0));
    }

    @Test
    public void testUpdateLoan() throws Exception {
        Loan loan = new Loan();
        loan.setId(1);
        loan.setAccountId(1);
        loan.setAmount(1000.0);

        mockMvc.perform(MockMvcRequestBuilders.put("/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loan.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(1000.0));
    }

    @Test
    public void testUpdateLoan_NotFound() throws Exception {
        Loan loan = new Loan();
        loan.setId(3);
        loan.setAccountId(1);
        loan.setAmount(1000.0);

        mockMvc.perform(MockMvcRequestBuilders.put("/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loan.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteLoan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDeleteLoan_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}