package com.revature.revpay.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer>{
    Optional<Loan> findByLoanId(Integer loan_id);
    Optional<Loan> findByLoanAmount(Double loan_amount);
    Optional<Loan> findByLoanStatus(String loan_status);
    Optional<Loan> findByLoanDate(String loan_date);
    Optional<Loan> findByInterestRate(Double interest_rate);
    Optional<Loan> findByStartDate(String start_date);
    Optional<Loan> findByDueDate(String due_date);
    List<Loan> findByAccountId(Account account_id);
    Boolean deleteByLoanId(Integer loan_id);


}
