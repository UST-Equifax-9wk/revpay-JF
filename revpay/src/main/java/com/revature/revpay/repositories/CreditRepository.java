package com.revature.revpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Credit;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer> {
    Optional<Credit> findByCredit_id(Integer credit_id);
    Optional<Credit> findByCredit_num(String credit_num);
    Optional<Credit> findByCredit_exp(String credit_exp);
    Optional<Credit> findByCredit_cvv(String credit_cvv);
    Optional<Credit> findByFirst_name(String first_name);
    Optional<Credit> findByLast_name(String last_name);
    
}
