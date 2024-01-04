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

/**
 * Represents a credit card entity.
 */
@Entity
@Table(name = "card")
public class Card {
    @Id
    @Column(name = "cardId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(name = "cardHolder", nullable = false, length = 60)
    private String cardHolder;

    @Column(name = "cardNum", nullable = false, length = 16)
    private String cardNum;

    @Column(name = "cardExp", nullable = false, length = 10)
    private String cardExp;

    @Column(name = "cardCvv", nullable = false, length = 3)
    private String cardCvv;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference("card_account")
    private Account accountId;

    /**
     * Constructs a new Card object with the specified cardholder, card number, card expiration date, card CVV, and account ID.
     * 
     * @param cardholder the name of the cardholder
     * @param card_num the card number
     * @param card_exp the card expiration date
     * @param card_cvv the card CVV
     * @param account_id the ID of the associated account
     */
    public Card(String cardholder, String card_num, String card_exp, String card_cvv, Account account_id) {
        this.cardNum = card_num;
        this.cardExp = card_exp;
        this.cardHolder = cardholder;
        this.cardCvv = card_cvv;
        this.accountId = account_id;
    }

    /**
     * Constructs a new Card object.
     */
    public Card() {
        super();
    }

    // Getters

    /**
     * Returns the ID of the card.
     * 
     * @return the card ID
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * Returns the name of the cardholder.
     * 
     * @return the cardholder name
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * Returns the card number.
     * 
     * @return the card number
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * Returns the card expiration date.
     * 
     * @return the card expiration date
     */
    public String getCardExp() {
        return cardExp;
    }

    /**
     * Returns the card CVV.
     * 
     * @return the card CVV
     */
    public String getCardCvv() {
        return cardCvv;
    }

    /**
     * Returns the associated account.
     * 
     * @return the associated account
     */
    public Account getAccountId() {
        return accountId;
    }

    // Setters

    /**
     * Sets the ID of the card.
     * 
     * @param cardId the card ID to set
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * Sets the name of the cardholder.
     * 
     * @param cardHolder the cardholder name to set
     */
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    /**
     * Sets the card number.
     * 
     * @param cardNum the card number to set
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * Sets the card expiration date.
     * 
     * @param cardExp the card expiration date to set
     */
    public void setCardExp(String cardExp) {
        this.cardExp = cardExp;
    }

    /**
     * Sets the card CVV.
     * 
     * @param cardCvv the card CVV to set
     */
    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    /**
     * Sets the associated account.
     * 
     * @param accountId the associated account to set
     */
    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Card [cardId=" + cardId + ", cardHolder=" + cardHolder + ", cardNum=" + cardNum + ", cardExp=" + cardExp
                + ", cardCvv=" + cardCvv + ", accountId=" + accountId + "]";
    }
}
