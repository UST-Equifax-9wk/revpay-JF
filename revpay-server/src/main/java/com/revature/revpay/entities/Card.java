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

    public Card(String cardholder, String card_num, String card_exp, String card_cvv, Account account_id) {
        this.cardNum = card_num;
        this.cardExp = card_exp;
        this.cardHolder = cardholder;
        this.cardCvv = card_cvv;
        this.accountId = account_id;
    }

    public Card() {
        super();
    }


    // Getters
    public Integer getCardId() {
        return cardId;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardExp() {
        return cardExp;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public Account getAccountId() {
        return accountId;
    }

    // Setters
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setCardExp(String cardExp) {
        this.cardExp = cardExp;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }


    @Override
    public String toString() {
        return "Card [cardId=" + cardId + ", cardHolder=" + cardHolder + ", cardNum=" + cardNum + ", cardExp=" + cardExp
                + ", cardCvv=" + cardCvv + ", accountId=" + accountId + "]";
    }



}
