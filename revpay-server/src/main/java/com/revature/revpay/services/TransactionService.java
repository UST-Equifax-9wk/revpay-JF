package com.revature.revpay.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.dto.TransactionDto;
import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.Transaction;
import com.revature.revpay.repositories.AccountRepository;
import com.revature.revpay.repositories.TransactionRepository;

/**
 * The TransactionService class provides methods for managing transactions.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param transaction_id The ID of the transaction.
     * @return The transaction with the specified ID.
     * @throws NoSuchElementException if no transaction with the specified ID is found.
     */
    public Transaction getTransactionById(Integer transaction_id) {
        return transactionRepository.findByTransactionId(transaction_id).orElseThrow();
    }

    /**
     * Finds a transaction by its amount.
     *
     * @param transaction_amount The amount of the transaction.
     * @return The transaction with the specified amount.
     * @throws NoSuchElementException if no transaction with the specified amount is found.
     */
    Transaction findTransactionByTransaction_amount(Double transaction_amount) {
        return transactionRepository.findByAmount(transaction_amount).orElseThrow();
    }

    /**
     * Retrieves all transactions associated with a specific account.
     *
     * @param account_id The ID of the account.
     * @return A set of transactions associated with the specified account.
     * @throws NoSuchElementException if no account with the specified ID is found.
     */
    public Set<Transaction> findAllTransactionsByAccountId(Integer account_id) {
        Account account = accountRepository.findByAccountId(account_id).orElseThrow();
        Set<Transaction> transactions = new HashSet<Transaction>();
        transactions.addAll(transactionRepository.findAllBySenderAccount(account));
        transactions.addAll(transactionRepository.findAllByReceiverAccount(account));
        return transactions;
    }

    /**
     * Finds a transaction by the receiver account ID.
     *
     * @param account_id The ID of the receiver account.
     * @return The transaction with the specified receiver account ID.
     * @throws NoSuchElementException if no transaction with the specified receiver account ID is found.
     */
    public Transaction findTransactionByReceiver_account(Integer account_id) {
        Account account = accountRepository.findByAccountId(account_id).orElseThrow();
        return transactionRepository.findByReceiverAccount(account).orElseThrow();
    }

    /**
     * Adds a new transaction.
     *
     * @param transactionDto The transaction data transfer object.
     * @return The newly added transaction.
     */
    public Transaction addTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setReceiverAccount(accountRepository.findByAccountId(transactionDto.getSenderAccountId()).get());
        transaction.setSenderAccount(accountRepository.findByAccountId(transactionDto.getSenderAccountId()).get());
        return transactionRepository.save(transaction);
    }

    /**
     * Updates an existing transaction.
     *
     * @param transaction The transaction to be updated.
     * @return The updated transaction.
     */
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
