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
 * Represents a loan entity in the system.
 */
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

    /**
     * Constructs a new Loan object with the specified parameters.
     * 
     * @param loanId       the ID of the loan
     * @param accountId    the account ID associated with the loan
     * @param loanAmount   the amount of the loan
     * @param loanStatus   the status of the loan (approved, denied, pending)
     * @param loanDate     the date of the loan
     * @param interestRate the interest rate of the loan
     * @param startDate    the start date of the loan
     * @param dueDate      the due date of the loan
     */
    public Loan(Integer loanId, Account accountId, Double loanAmount, String loanStatus, String loanDate,
            Double interestRate, String startDate, String dueDate) {
        this.loanId = loanId;
        this.accountId = accountId;
        this.loanAmount = loanAmount;
        this.loanStatus = loanStatus;
        this.loanDate = loanDate;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    /**
     * Default constructor for Loan class.
     */
    public Loan() {
    }

    /**
     * Sets the ID of the loan.
     * 
     * @param loanId the ID of the loan
     */
    public void setId(int loanId) {
        this.loanId = loanId;
    }

    /**
     * Sets the account ID associated with the loan.
     * 
     * @param accountId the account ID associated with the loan
     */
    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    /**
     * Sets the amount of the loan.
     * 
     * @param loanAmount the amount of the loan
     */
    public void setAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
}
