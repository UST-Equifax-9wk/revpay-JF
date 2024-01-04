package com.revature.revpay.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.User;
import com.revature.revpay.repositories.AccountRepository;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAccountById() {
        Account account = new Account();
        account.setAccountId(1);
        account.setAccountType(true);
        account.setAccountName("Savings");
        account.setBalance(1000.0);

        when(accountRepository.findByAccountId(1)).thenReturn(Optional.of(account));

        Account result = accountService.findAccountById(1);

        assertEquals(account, result);
    }

    @Test
    public void testFindAccountsByUserId() {
        Account account1 = new Account();
        account1.setAccountId(1);
        account1.setAccountType(true);
        account1.setAccountName("Savings");
        account1.setBalance(1000.0);

        Account account2 = new Account();
        account2.setAccountId(2);
        account2.setAccountType(false);
        account2.setAccountName("Checking");
        account2.setBalance(500.0);

        Set<Account> accounts = new HashSet<>();
        accounts.add(account1);
        accounts.add(account2);

        when(accountRepository.findAllAccountsByUserId(1)).thenReturn(accounts);

        Set<Account> result = accountService.findAccountsByUserId(1);

        assertEquals(accounts, result);
    }

    @Test
    public void testFindAccountByAccountType() {
        Account account = new Account();
        account.setAccountId(1);
        account.setAccountType(true);
        account.setAccountName("Savings");
        account.setBalance(1000.0);

        when(accountRepository.findByAccountType(true)).thenReturn(Optional.of(account));

        Account result = accountService.findAccountByAccountType(true);

        assertEquals(account, result);
    }

    @Test
    public void testAddAccount() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        Account account = new Account();
        account.setAccountId(1);
        account.setAccountType(true);
        account.setAccountName("Savings");
        account.setBalance(1000.0);

        when(userService.findUserById(1)).thenReturn(user);
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.addAccount(1, account);

        assertEquals(account, result);
        assertEquals(user, account.getUserId());
    }

    @Test
    public void testUpdateAccount() {
        Account existingAccount = new Account();
        existingAccount.setAccountId(1);
        existingAccount.setAccountType(true);
        existingAccount.setAccountName("Savings");
        existingAccount.setBalance(1000.0);

        Account updatedAccount = new Account();
        updatedAccount.setAccountId(1);
        updatedAccount.setAccountType(false);
        updatedAccount.setAccountName("Checking");
        updatedAccount.setBalance(500.0);

        when(accountRepository.findByAccountId(1)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(existingAccount)).thenReturn(updatedAccount);

        Account result = accountService.updateAccount(1, updatedAccount);

        assertEquals(updatedAccount, result);
        assertEquals(updatedAccount.getAccountType(), existingAccount.getAccountType());
        assertEquals(updatedAccount.getAccountName(), existingAccount.getAccountName());
        assertEquals(updatedAccount.getBalance(), existingAccount.getBalance());
    }

    @Test
    public void testDeleteAccount() {
        when(accountRepository.deleteByAccountId(1)).thenReturn(true);

        boolean result = accountService.deleteAccount(1);

        assertTrue(result);
    }
}