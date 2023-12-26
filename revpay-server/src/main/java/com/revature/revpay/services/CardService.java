package com.revature.revpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Card;
import com.revature.revpay.repositories.CardRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public Card findCardById(Integer card_id) {
        return cardRepository.findByCard_id(card_id).orElseThrow();
    }

    public Card findCardByCard_number(String card_number) {
        return cardRepository.findByCard_number(card_number).orElseThrow();
    }

    public Card findCardByCard_type(String card_type) {
        return cardRepository.findByCard_type(card_type).orElseThrow();
    }

    public Card findCardByCard_holder(String card_holder) {
        return cardRepository.findByCard_holder(card_holder).orElseThrow();
    }

    public Card findCardByCard_cvv(String card_cvv) {
        return cardRepository.findByCard_cvv(card_cvv).orElseThrow();
    }

    public Card findCardByCard_exp(String card_exp) {
        return cardRepository.findByCard_exp(card_exp).orElseThrow();
    }

    public Card findCardByAccount_id(Integer account_id) {
        return cardRepository.findByAccount_id(account_id).orElseThrow();
    }

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public void deleteCard(Integer card_id) {
        cardRepository.deleteByCard_id(card_id);
    }
    
}
