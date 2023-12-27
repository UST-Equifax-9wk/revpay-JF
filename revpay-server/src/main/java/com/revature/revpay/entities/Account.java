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
    @Column(name = "accountId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "accountNumber", nullable = false, length = 60)
    private String accountNumber;

    @Column(name = "balance", nullable = false, length = 60)
    private Double balance;

    @Column(name = "accountType", nullable = false)
    private Boolean accountType; // one for business, zero for personal

    public Account(Integer account_id, User user, String account_Number, Double balance, Boolean account_type) {
        this.accountId = account_id;
        this.user = user;
        this.accountNumber = account_Number;
        this.balance = balance;
        this.accountType = account_type;

    }

    public Account() {
    }

    public void setId(int i) {
    }

    public void setAccountNumber(String string) {
    }

    public void setAccountHolderName(String string) {
    }

    public void setBalance(double d) {
    }

    public void setAccountId(int accountId) {
    }

    public void setUserId(int userId) {
    }

    public void setAccountType(boolean accountType) {
    }

    
    
}
