package com.revature.revpay.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    Optional<Account> findByAccountId(Integer account_id);

    @Query(value = "SELECT * FROM Account a WHERE a.user_Id = :user_id", nativeQuery = true)
    Set<Account> findAllAccountsByUserId(@Param("user_id") Integer user_id);

    Optional<Account> findByAccountType(Boolean account_type);
    Boolean deleteByAccountId(Integer account_id);

}
