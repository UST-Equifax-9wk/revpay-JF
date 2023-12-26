package com.revature.revpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    Optional<Account> findByAccount_id(Integer account_id);
    Optional<Account> findByAccount_balance(Double account_balance);
    Optional<Account> findByUser_id(Integer user_id);
    Optional<Account> findByAccount_type(Boolean account_type);

}
