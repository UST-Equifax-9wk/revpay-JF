package com.revature.revpay.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.Transaction;
import com.revature.revpay.repositories.AccountRepository;
import com.revature.revpay.repositories.TransactionRepository;

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

    public Transaction getTransactionById(Integer transaction_id) {
        return transactionRepository.findByTransactionId(transaction_id).orElseThrow();
    }

    Transaction findTransactionByTransaction_amount(Double transaction_amount) {
        return transactionRepository.findByAmount(transaction_amount).orElseThrow();
    }

    Transaction findTransactionByTransaction_type(String transaction_type) {
        return transactionRepository.findByTransactionType(transaction_type).orElseThrow();
    }

    Transaction findTransactionByTransaction_date(String transaction_date) {
        return transactionRepository.findByTransactionDate(transaction_date).orElseThrow();
    }

    public Set<Transaction> findAllTransactionsByAccountId(Integer account_id) {
        Account account = accountRepository.findByAccountId(account_id).orElseThrow();
        Set<Transaction> transactions = new HashSet<Transaction>();
        transactions.addAll(transactionRepository.findAllBySenderAccount(account));
        transactions.addAll(transactionRepository.findAllByReceiverAccount(account));
        return transactions;
    }

    public Transaction findTransactionByReceiver_account(Integer account_id) {
        Account account = accountRepository.findByAccountId(account_id).orElseThrow();
        return transactionRepository.findByReceiverAccount(account).orElseThrow();
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }




}
