package com.revature.revpay.entities;
 
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    @Column(name = "transactionType", nullable = false)
    private String transactionType;

    @Column(name = "transactionStatus", nullable = false)
    private String transactionStatus;

    @Column(name = "transactionDate", nullable = false)
    private String transactionDate;

    public Transaction(Integer transaction_id, Account sender_account, Account receiver_account, Double amount, String transaction_type, String transaction_status, String transaction_date) {
        this.transactionId = transaction_id;
        this.senderAccount = sender_account;
        this.receiverAccount = receiver_account;
        this.amount = amount;
        this.transactionType = transaction_type;
        this.transactionStatus = transaction_status;
        this.transactionDate = transaction_date;
    }
}
