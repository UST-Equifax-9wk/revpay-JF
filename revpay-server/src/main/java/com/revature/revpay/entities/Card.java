package com.revature.revpay.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "card", indexes = {@Index(columnList = "cardId", unique = true)})
public class Card {
    @Id
    @Column(name = "cardId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(name = "firstName", nullable = false, length = 60)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 60)
    private String lastName;

    @Column(name = "cardNum", nullable = false, length = 16)
    private String cardNum;

    @Column(name = "cardExp", nullable = false, length = 4)
    private String cardExp;

    @Column(name = "cardCvv", nullable = false, length = 3)
    private String cardCvv;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    @JsonBackReference
    private Account accountId;

    public Card( Integer card_id, String first_name, String last_name, String card_num, String card_exp, String card_cvv, Account account_id) {
        this.cardId = card_id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.cardNum = card_num;
        this.cardExp = card_exp;
        this.cardCvv = card_cvv;
        this.accountId = account_id;
    }

    public Card() {
    }

    public void setCardId(int i) {
        this.cardId = i;
    }

    public void setCardNumber(String string) {
        this.cardNum = string;
    }

    public void setCardHolderName(String string) {
        this.firstName = string;
    }

    public void setExpirationDate(String string) {
        this.cardExp = string;
    }

    public void setCvv(String string) {
        this.cardCvv = string;
    }

    public Integer get_id() {
        return cardId;
    }


}
