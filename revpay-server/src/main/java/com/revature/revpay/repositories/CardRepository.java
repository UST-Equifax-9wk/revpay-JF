package com.revature.revpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByCardId(Integer card_id);
    Optional<Card> findByCardNum(String card_number);
    Optional<Card> findByFirstName(String card_holder);
    Optional<Card> findByCardCvv(String card_cvv);
    Optional<Card> findByCardExp(String card_exp);
    Optional<Card> findByAccountId(Account account_id);
    Boolean deleteByCardId(Integer card_id);
    void deleteByCardNum(String card_number);

    
}
