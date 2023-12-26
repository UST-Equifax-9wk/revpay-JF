package com.revature.revpay.entities;
 
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;

    @ManyToOne
    @Column(name = "sender_account", nullable = false)
    @JsonBackReference
    private Account sender_account;
    
    @ManyToOne
    @Column(name = "receiver_account", nullable = false)
    @JsonBackReference
    private Account receiver_account;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "transaction_type", nullable = false)
    private String transaction_type;

    @Column(name = "transaction_status", nullable = false)
    private String transaction_status;

    @Column(name = "transaction_date", nullable = false)
    private String transaction_date;

    public Transaction(Integer transaction_id, Account sender_account, Account receiver_account, Double amount, String transaction_type, String transaction_status, String transaction_date) {
        this.transaction_id = transaction_id;
        this.sender_account = sender_account;
        this.receiver_account = receiver_account;
        this.amount = amount;
        this.transaction_type = transaction_type;
        this.transaction_status = transaction_status;
        this.transaction_date = transaction_date;
    }
}
