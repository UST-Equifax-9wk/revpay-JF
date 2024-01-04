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

/**
 * Represents an account entity in the system.
 */
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

    /**
     * Constructs an Account object with the specified parameters.
     * 
     * @param accountId       the account ID
     * @param user            the user associated with the account
     * @param accountName     the name of the account
     * @param balance         the balance of the account
     * @param accountType     the type of the account (true for business, false for personal)
     * @param transactionsOut the set of outgoing transactions associated with the account
     * @param transactionsIn  the set of incoming transactions associated with the account
     * @param cards           the set of cards associated with the account
     */
    public Account(Integer accountId, User user, String accountName, Double balance, Boolean accountType,
            Set<Transaction> transactionsOut, Set<Transaction> transactionsIn, Set<Card> cards) {
        this.accountId = accountId;
        this.userId = user;
        this.accountName = accountName;
        this.balance = balance;
        this.accountType = accountType;
        this.transactionsOut = transactionsOut;
        this.transactionsIn = transactionsIn;
        this.cards = cards;
    }

    /**
     * Default constructor for the Account class.
     */
    public Account() {
    }

    /**
     * Retrieves the account ID.
     * 
     * @return the account ID
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * Sets the account ID.
     * 
     * @param accountId the account ID to set
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * Retrieves the user associated with the account.
     * 
     * @return the user associated with the account
     */
    public User getUserId() {
        return userId;
    }

    /**
     * Sets the user associated with the account.
     * 
     * @param user the user to set
     */
    public void setUserId(User user) {
        this.userId = user;
    }

    /**
     * Retrieves the name of the account.
     * 
     * @return the name of the account
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the name of the account.
     * 
     * @param accountName the name of the account to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Retrieves the balance of the account.
     * 
     * @return the balance of the account
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account.
     * 
     * @param balance the balance of the account to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * Retrieves the type of the account.
     * 
     * @return the type of the account (true for business, false for personal)
     */
    public Boolean getAccountType() {
        return accountType;
    }

    /**
     * Sets the type of the account.
     * 
     * @param accountType the type of the account to set (true for business, false for personal)
     */
    public void setAccountType(Boolean accountType) {
        this.accountType = accountType;
    }

    /**
     * Returns a string representation of the Account object.
     * 
     * @return a string representation of the Account object
     */
    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", userId=" + userId + ", accountName=" + accountName + ", balance="
                + balance + ", accountType=" + accountType + "]";
    }
}

