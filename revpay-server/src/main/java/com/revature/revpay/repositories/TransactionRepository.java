package com.revature.revpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    Optional<Transaction> findByTransaction_id(Integer transaction_id);
    Optional<Transaction> findByTransaction_amount(Double transaction_amount);
    Optional<Transaction> findByTransaction_type(String transaction_type);
    Optional<Transaction> findByTransaction_status(String transaction_status);
    Optional<Transaction> findByTransaction_date(String transaction_date);
    Optional<Transaction> findBySender_account(Integer sender_account);
    Optional<Transaction> findByReceiver_account(Integer receiver_account);
}
