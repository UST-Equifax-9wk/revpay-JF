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
@Table(name = "loan")
public class Loan {

    @Id
    @Column(name = "loanId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    @JsonBackReference
    private Account accountId;

    @Column(name = "loanAmount", nullable = false)
    private Double loanAmount;

    @Column(name = "loanStatus", nullable = false)
    private String loanStatus; //approved, denied, pending

    @Column(name = "loanDate", nullable = false)
    private String loanDate;

    @Column(name = "interestRate", nullable = false)
    private Double interestRate;

    @Column(name = "startDate", nullable = false)
    private String startDate;

    @Column(name = "dueDate", nullable = false)
    private String dueDate;

    public Loan(Integer loan_id, Account account_id, Double loan_amount, String loan_status, String loan_date, Double interest_rate, String start_date, String due_date) {
        this.loanId = loan_id;
        this.accountId = account_id;
        this.loanAmount = loan_amount;
        this.loanStatus = loan_status;
        this.loanDate = loan_date;
        this.interestRate = interest_rate;
        this.startDate = start_date;
        this.dueDate = due_date;
    }

    public Loan() {
    }

    public void setId(int i) {
    }

    public void setAccountId(int i) {
    }

    public void setAmount(double d) {
    }
}
