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
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "account_Number", nullable = false, length = 60)
    private String account_Number;

    @Column(name = "balance", nullable = false, length = 60)
    private String balance;

    @Column(name = "account_type", nullable = false)
    private Boolean account_type; // one for business, zero for personal

    public Account(Integer account_id, User user, String account_Number, String balance, Boolean account_type) {
        this.account_id = account_id;
        this.user = user;
        this.account_Number = account_Number;
        this.balance = balance;
        this.account_type = account_type;

    }

    
    
}
