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
@Table(name = "loan")
public class Loan {

    @Id
    @Column(name = "loan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loan_id;

    @ManyToOne
    @Column(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account_id;

    @Column(name = "loan_amount", nullable = false)
    private Double loan_amount;

    @Column(name = "loan_status", nullable = false)
    private String loan_status; //approved, denied, pending

    @Column(name = "loan_date", nullable = false)
    private String loan_date;

    @Column(name = "interest_rate", nullable = false)
    private Double interest_rate;

    @Column(name = "start_date", nullable = false)
    private String start_date;

    @Column(name = "due_date", nullable = false)
    private String due_date;

    public Loan(Integer loan_id, Account account_id, Double loan_amount, String loan_status, String loan_date, Double interest_rate, String start_date, String due_date) {
        this.loan_id = loan_id;
        this.account_id = account_id;
        this.loan_amount = loan_amount;
        this.loan_status = loan_status;
        this.loan_date = loan_date;
        this.interest_rate = interest_rate;
        this.start_date = start_date;
        this.due_date = due_date;
    }
}
