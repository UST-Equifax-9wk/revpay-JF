package com.revature.revpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByCard_id(Integer card_id);
    Optional<Card> findByCard_number(String card_number);
    Optional<Card> findByCard_type(String card_type);
    Optional<Card> findByCard_status(String card_status);
    Optional<Card> findByCard_holder(String card_holder);
    Optional<Card> findByCard_cvv(String card_cvv);
    Optional<Card> findByCard_exp(String card_exp);
    Optional<Card> findByAccount_id(Integer account_id);
    void deleteByCard_id(Integer card_id);
    void deleteByCard_number(String card_number);

    
}
