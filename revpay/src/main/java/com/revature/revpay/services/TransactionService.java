package com.revature.revpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Transaction;
import com.revature.revpay.repositories.TransactionRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    Transaction findTransactionById(Integer transaction_id) {
        return transactionRepository.findByTransaction_id(transaction_id).orElseThrow();
    }

    Transaction findTransactionByTransaction_amount(Double transaction_amount) {
        return transactionRepository.findByTransaction_amount(transaction_amount).orElseThrow();
    }

    Transaction findTransactionByTransaction_type(String transaction_type) {
        return transactionRepository.findByTransaction_type(transaction_type).orElseThrow();
    }

    Transaction findTransactionByTransaction_date(String transaction_date) {
        return transactionRepository.findByTransaction_date(transaction_date).orElseThrow();
    }

    Transaction findTransactionBySender_account(Integer account_id) {
        return transactionRepository.findBySender_account(account_id).orElseThrow();
    }

    Transaction findTransactionByReceiver_account(Integer account_id) {
        return transactionRepository.findByReceiver_account(account_id).orElseThrow();
    }

    Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }


}
