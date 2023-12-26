package com.revature.revpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer>{
    Optional<Loan> findByLoan_id(Integer loan_id);
    Optional<Loan> findByLoan_amount(Double loan_amount);
    Optional<Loan> findByLoan_status(String loan_status);
    Optional<Loan> findByLoan_date(String loan_date);
    Optional<Loan> findByInterest_rate(Double interest_rate);
    Optional<Loan> findByStart_date(String start_date);
    Optional<Loan> findByDue_date(String due_date);
    Optional<Loan> findByAccount_id(Integer account_id);


}
