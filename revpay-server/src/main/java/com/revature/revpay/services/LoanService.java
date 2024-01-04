package com.revature.revpay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.Loan;
import com.revature.revpay.repositories.AccountRepository;
import com.revature.revpay.repositories.LoanRepository;

/**
 * The LoanService class provides methods for managing loans.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class LoanService {
    private final LoanRepository loanRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, AccountRepository AccountRepository) {
        this.loanRepository = loanRepository;
        this.accountRepository = AccountRepository;
    }

    /**
     * Finds a loan by its ID.
     *
     * @param loan_id The ID of the loan.
     * @return The loan with the specified ID.
     * @throws RuntimeException if the loan is not found.
     */
    public Loan findLoanById(Integer loan_id) {
        return loanRepository.findByLoanId(loan_id).orElseThrow();
    }
    
    /**
     * Finds a loan by its loan amount.
     *
     * @param loan_amount The loan amount.
     * @return The loan with the specified loan amount.
     * @throws RuntimeException if the loan is not found.
     */
    public Loan findLoanByLoan_amount(Double loan_amount) {
        return loanRepository.findByLoanAmount(loan_amount).orElseThrow();
    }

    /**
     * Finds a loan by its loan status.
     *
     * @param loan_status The loan status.
     * @return The loan with the specified loan status.
     * @throws RuntimeException if the loan is not found.
     */
    public Loan findLoanByLoan_status(String loan_status) {
        return loanRepository.findByLoanStatus(loan_status).orElseThrow();
    }

    /**
     * Finds a loan by its loan date.
     *
     * @param loan_date The loan date.
     * @return The loan with the specified loan date.
     * @throws RuntimeException if the loan is not found.
     */
    public Loan findLoanByLoan_date(String loan_date) {
        return loanRepository.findByLoanDate(loan_date).orElseThrow();
    }

    /**
     * Finds a loan by its interest rate.
     *
     * @param interest_rate The interest rate.
     * @return The loan with the specified interest rate.
     * @throws RuntimeException if the loan is not found.
     */
    public Loan findLoanByInterest_rate(Double interest_rate) {
        return loanRepository.findByInterestRate(interest_rate).orElseThrow();
    }

    /**
     * Finds a loan by its start date.
     *
     * @param start_date The start date.
     * @return The loan with the specified start date.
     * @throws RuntimeException if the loan is not found.
     */
    public Loan findLoanByStart_date(String start_date) {
        return loanRepository.findByStartDate(start_date).orElseThrow();
    }

    /**
     * Finds a loan by its due date.
     *
     * @param due_date The due date.
     * @return The loan with the specified due date.
     * @throws RuntimeException if the loan is not found.
     */
    public Loan findLoanByDue_date(String due_date) {
        return loanRepository.findByDueDate(due_date).orElseThrow();
    }

    /**
     * Finds all loans associated with a specific account.
     *
     * @param account_id The ID of the account.
     * @return A list of loans associated with the specified account.
     * @throws RuntimeException if the account is not found.
     */
    public List<Loan> findAllLoansByAccount_id(Integer account_id) {
        Account account = accountRepository.findByAccountId(account_id).orElseThrow();
        return loanRepository.findByAccountId(account);
    }

    /**
     * Adds a new loan.
     *
     * @param loan The loan to be added.
     * @return The added loan.
     */
    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    /**
     * Updates an existing loan.
     *
     * @param loan The loan to be updated.
     * @return The updated loan.
     */
    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    /**
     * Deletes a loan by its ID.
     *
     * @param id The ID of the loan to be deleted.
     * @return true if the loan is successfully deleted, false otherwise.
     */
    public boolean deleteLoan(int id) {
        return loanRepository.deleteByLoanId(id);
    }
}
