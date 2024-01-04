package com.revature.revpay.repositories;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    Optional<Transaction> findByTransactionId(Integer transaction_id);
    Optional<Transaction> findByAmount(Double transaction_amount);
    Optional<Transaction> findByTransactionDate(LocalDateTime transaction_date);
    Optional<Transaction> findByReceiverAccount(Account receiver_account);
    Set<Transaction> findAllBySenderAccount(Account sender_account);
    Set<Transaction> findAllByReceiverAccount(Account account);
}
