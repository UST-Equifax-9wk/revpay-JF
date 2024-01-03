package com.revature.revpay.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.revpay.entities.Account;
import com.revature.revpay.repositories.AccountRepository;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAccountById() {
        // Arrange
        int accountId = 1;
        Account account = new Account();
        account.setAccountId(accountId);
        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.of(account));

        // Act
        Account result = accountService.findAccountById(accountId);

        // Assert
        assertEquals(account, result);
    }

    @Test
    public void testFindAccountByUser_id() {
        // Arrange
        int userId = 1;
        Account account = new Account();
        account.setAccountId(1);
        account.setUserId(userId);
        when(accountRepository.findAllAccountsByUser_UserId(userId)).thenReturn(Set.of(account));

        // Act
        Set<Account> result = accountService.findAccountsByUserId(userId);

        // Assert
        assertEquals(account, result);
    }

    @Test
    public void testFindAccountByAccount_type() {
        // Arrange
        boolean accountType = true;
        Account account = new Account();
        account.setAccountId(1);
        account.setAccountType(accountType);
        when(accountRepository.findByAccountType(accountType)).thenReturn(Optional.of(account));

        // Act
        Account result = accountService.findAccountByAccount_type(accountType);

        // Assert
        assertEquals(account, result);
    }

    @Test
    public void testAddAccount() {
        // Arrange
        Account account = new Account();
        when(accountRepository.save(account)).thenReturn(account);

        // Act
        Account result = accountService.addAccount(account);

        // Assert
        assertEquals(account, result);
    }

    @Test
    public void testUpdateAccount() {
        // Arrange
        Account account = new Account();
        when(accountRepository.save(account)).thenReturn(account);

        // Act
        Account result = accountService.updateAccount(account);

        // Assert
        assertEquals(account, result);
    }

    @Test
    public void testDeleteAccount() {
        // Arrange
        int accountId = 1;
        when(accountRepository.deleteByAccountId(accountId)).thenReturn(true);

        // Act
        Boolean result = accountService.deleteAccount(accountId);

        // Assert
        assertTrue(result);
    }
}