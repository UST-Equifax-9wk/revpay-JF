package com.revature.revpay.controllers;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpay.dto.TransactionDto;
import com.revature.revpay.entities.Transaction;
import com.revature.revpay.services.TransactionService;

/**
 * The TransactionController class handles HTTP requests related to transactions.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Retrieves all transactions for a given account number.
     *
     * @param accountNumber The account number to retrieve transactions for.
     * @return A set of transactions associated with the account number.
     */
    @GetMapping("/transactions/{accountNumber}")
    public Set<Transaction> getAllTransactions(@PathVariable Integer accountNumber) {
        return transactionService.findAllTransactionsByAccountId(accountNumber);
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id The ID of the transaction to retrieve.
     * @return The transaction with the specified ID.
     */
    @GetMapping("/transactions/{id}")
    public Transaction getTransactionById(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }

    /**
     * Creates a new transaction.
     *
     * @param transactionDto The transaction data to create the transaction.
     * @return The created transaction.
     */
    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.addTransaction(transactionDto);
    }

    /**
     * Updates an existing transaction.
     *
     * @param transaction The transaction data to update the transaction.
     * @return The updated transaction.
     */
    @PutMapping("/transactions")
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return transactionService.updateTransaction(transaction);
    }
}
