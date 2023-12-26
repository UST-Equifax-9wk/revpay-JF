package com.revature.revpay.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(indexes = {@Index(name = "user_id", columnList = "id", unique = true)})
public class Card {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer card_id;

    @Column(name = "first_name", nullable = false, length = 60)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 60)
    private String last_name;

    @Column(name = "card_num", nullable = false, length = 16)
    private String card_num;

    @Column(name = "card_exp", nullable = false, length = 4)
    private String card_exp;

    @Column(name = "credit_cvv", nullable = false, length = 3)
    private String card_cvv;

    @ManyToOne
    @Column(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account_id;

    public Card( Integer card_id, String first_name, String last_name, String card_num, String card_exp, String card_cvv, Account account_id) {
        this.card_id = card_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.card_num = card_num;
        this.card_exp = card_exp;
        this.card_cvv = card_cvv;
        this.account_id = account_id;
    }


}
