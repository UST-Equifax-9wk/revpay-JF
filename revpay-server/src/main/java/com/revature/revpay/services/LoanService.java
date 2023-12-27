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

    public Loan findLoanById(Integer loan_id) {
        return loanRepository.findByLoanId(loan_id).orElseThrow();
    }
    
    public Loan findLoanByLoan_amount(Double loan_amount) {
        return loanRepository.findByLoanAmount(loan_amount).orElseThrow();
    }

    public Loan findLoanByLoan_status(String loan_status) {
        return loanRepository.findByLoanStatus(loan_status).orElseThrow();
    }

    public Loan findLoanByLoan_date(String loan_date) {
        return loanRepository.findByLoanDate(loan_date).orElseThrow();
    }

    public Loan findLoanByInterest_rate(Double interest_rate) {
        return loanRepository.findByInterestRate(interest_rate).orElseThrow();
    }

    public Loan findLoanByStart_date(String start_date) {
        return loanRepository.findByStartDate(start_date).orElseThrow();
    }

    public Loan findLoanByDue_date(String due_date) {
        return loanRepository.findByDueDate(due_date).orElseThrow();
    }

    public List<Loan> findAllLoansByAccount_id(Integer account_id) {
        Account account = accountRepository.findByAccountId(account_id).orElseThrow();
        return loanRepository.findByAccountId(account);
    }

    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public boolean deleteLoan(int id) {
        return loanRepository.deleteByLoanId(id);
    }



    
}
