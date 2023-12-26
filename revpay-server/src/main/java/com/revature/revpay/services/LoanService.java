package com.revature.revpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Loan;
import com.revature.revpay.repositories.LoanRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan findLoanById(Integer loan_id) {
        return loanRepository.findByLoan_id(loan_id).orElseThrow();
    }
    
    public Loan findLoanByLoan_amount(Double loan_amount) {
        return loanRepository.findByLoan_amount(loan_amount).orElseThrow();
    }

    public Loan findLoanByLoan_status(String loan_status) {
        return loanRepository.findByLoan_status(loan_status).orElseThrow();
    }

    public Loan findLoanByLoan_date(String loan_date) {
        return loanRepository.findByLoan_date(loan_date).orElseThrow();
    }

    public Loan findLoanByInterest_rate(Double interest_rate) {
        return loanRepository.findByInterest_rate(interest_rate).orElseThrow();
    }

    public Loan findLoanByStart_date(String start_date) {
        return loanRepository.findByStart_date(start_date).orElseThrow();
    }

    public Loan findLoanByDue_date(String due_date) {
        return loanRepository.findByDue_date(due_date).orElseThrow();
    }

    public Loan findLoanByAccount_id(Integer account_id) {
        return loanRepository.findByAccount_id(account_id).orElseThrow();
    }

    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public void deleteLoan(Loan loan) {
        loanRepository.delete(loan);
    }

    
}
