package com.revature.revpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    Optional<Account> findByAccountId(Integer account_id);
    Optional<Account> findByUser_UserId(Integer user_id);
    Optional<Account> findByAccountType(Boolean account_type);
    Boolean deleteByAccountId(Integer account_id);

}
