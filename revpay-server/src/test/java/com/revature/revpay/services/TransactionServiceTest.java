package com.revature.revpay.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.revpay.dto.TransactionDto;
import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.Transaction;
import com.revature.revpay.repositories.AccountRepository;
import com.revature.revpay.repositories.TransactionRepository;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTransactionById() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setAmount(100.0);
        transaction.setTransactionDate(LocalDateTime.now());

        when(transactionRepository.findByTransactionId(1)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.getTransactionById(1);

        assertEquals(transaction, result);
    }

    @Test
    public void testFindTransactionByTransaction_amount() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setAmount(100.0);
        transaction.setTransactionDate(LocalDateTime.now());

        when(transactionRepository.findByAmount(100.0)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.findTransactionByTransaction_amount(100.0);

        assertEquals(transaction, result);
    }

    @Test
    public void testFindAllTransactionsByAccountId() {
        Account account = new Account();
        account.setAccountId(1);

        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId(1);
        transaction1.setAmount(100.0);
        transaction1.setTransactionDate(LocalDateTime.now());
        transaction1.setSenderAccount(account);
        transaction1.setReceiverAccount(account);

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionId(2);
        transaction2.setAmount(200.0);
        transaction2.setTransactionDate(LocalDateTime.now());
        transaction2.setSenderAccount(account);
        transaction2.setReceiverAccount(account);

        Set<Transaction> transactions = new HashSet<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(accountRepository.findByAccountId(1)).thenReturn(Optional.of(account));
        when(transactionRepository.findAllBySenderAccount(account)).thenReturn(transactions);
        when(transactionRepository.findAllByReceiverAccount(account)).thenReturn(transactions);

        Set<Transaction> result = transactionService.findAllTransactionsByAccountId(1);

        assertEquals(transactions, result);
    }

    @Test
    public void testFindTransactionByReceiver_account() {
        Account account = new Account();
        account.setAccountId(1);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setAmount(100.0);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setReceiverAccount(account);

        when(accountRepository.findByAccountId(1)).thenReturn(Optional.of(account));
        when(transactionRepository.findByReceiverAccount(account)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.findTransactionByReceiver_account(1);

        assertEquals(transaction, result);
    }

    @Test
    public void testAddTransaction() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(100.0);
        transactionDto.setSenderAccountId(1);
        transactionDto.setReceiverAccountId(2);

        Account senderAccount = new Account();
        senderAccount.setAccountId(1);

        Account receiverAccount = new Account();
        receiverAccount.setAccountId(2);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setAmount(100.0);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(receiverAccount);

        when(accountRepository.findByAccountId(1)).thenReturn(Optional.of(senderAccount));
        when(accountRepository.findByAccountId(2)).thenReturn(Optional.of(receiverAccount));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction result = transactionService.addTransaction(transactionDto);

        assertEquals(transaction, result);
    }

    @Test
    public void testUpdateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setAmount(100.0);
        transaction.setTransactionDate(LocalDateTime.now());

        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction result = transactionService.updateTransaction(transaction);

        assertEquals(transaction, result);
    }
}