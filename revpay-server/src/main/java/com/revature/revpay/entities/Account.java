package com.revature.revpay.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @JsonBackReference(value = "user")
    private User userId;

    @Column(name = "accountName", nullable = false, length = 60)
    private String accountName;

    @Column(name = "balance", nullable = false, length = 60)
    private Double balance;

    @Column(name = "accountType", nullable = false)
    private Boolean accountType; // one for business, zero for personal

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "sending_account")
    private Set<Transaction> transactionsOut;

    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "receiving_account")
    private Set<Transaction> transactionsIn;

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "card_account")
    private Set<Card> cards;

    public Account(Integer account_id, User user, String account_Name, Double balance, Boolean account_type, Set<Transaction> transactionsOut, Set<Transaction> transactionsIn, Set<Card> cards) {
        this.accountId = account_id;
        this.userId = user;
        this.balance = balance;
        this.accountType = account_type;
        this.accountName = account_Name;
        this.transactionsOut = transactionsOut;
        this.transactionsIn = transactionsIn;
        this.cards = cards;
    }

    public Account() {
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getAccountType() {
        return accountType;
    }

    public void setAccountType(Boolean accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", userId=" + userId + ", accountName=" + accountName + ", balance="
                + balance + ", accountType=" + accountType + "]";
    }
}

