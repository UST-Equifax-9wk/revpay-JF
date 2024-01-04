package com.revature.revpay.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a transaction between two accounts.
 */
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transactionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id_from", nullable = false)
    @JsonBackReference("sending_account")
    private Account senderAccount;
    
    @ManyToOne
    @JoinColumn(name = "account_id_to", nullable = false)
    @JsonBackReference("receiving_account")
    private Account receiverAccount;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "transactionDate", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime transactionDate;

    /**
     * Constructs a new Transaction object with the specified parameters.
     * 
     * @param transactionId    the ID of the transaction
     * @param senderAccount    the account from which the transaction is made
     * @param receiverAccount  the account to which the transaction is made
     * @param amount           the amount of the transaction
     */
    public Transaction(Integer transactionId, Account senderAccount, Account receiverAccount, Double amount) {
        this.transactionId = transactionId;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
    }

    /**
     * Constructs a new empty Transaction object.
     */
    public Transaction() {
        super();
    }

    // Getters and Setters

    /**
     * Returns the ID of the transaction.
     * 
     * @return the transaction ID
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the ID of the transaction.
     * 
     * @param transactionId the transaction ID to set
     */
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Returns the account from which the transaction is made.
     * 
     * @return the sender account
     */
    public Account getSenderAccount() {
        return senderAccount;
    }

    /**
     * Sets the account from which the transaction is made.
     * 
     * @param senderAccount the sender account to set
     */
    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    /**
     * Returns the account to which the transaction is made.
     * 
     * @return the receiver account
     */
    public Account getReceiverAccount() {
        return receiverAccount;
    }

    /**
     * Sets the account to which the transaction is made.
     * 
     * @param receiverAccount the receiver account to set
     */
    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    /**
     * Returns the amount of the transaction.
     * 
     * @return the transaction amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     * 
     * @param amount the transaction amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Returns the date and time of the transaction.
     * 
     * @return the transaction date and time
     */
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the date and time of the transaction.
     * 
     * @param transactionDate the transaction date and time to set
     */
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}

    
