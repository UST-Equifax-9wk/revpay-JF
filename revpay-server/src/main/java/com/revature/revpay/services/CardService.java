package com.revature.revpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.Card;
import com.revature.revpay.repositories.AccountRepository;
import com.revature.revpay.repositories.CardRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class CardService {
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CardService(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }


    public Card findCardById(Integer card_id) {
        return cardRepository.findByCardId(card_id).orElseThrow();
    }

    public Card findCardByCard_number(String card_number) {
        return cardRepository.findByCardNum(card_number).orElseThrow();
    }

    public Card findByFirst_Name(String firstName) {
        return cardRepository.findByFirstName(firstName).orElseThrow();
    }

    public Card findCardByLast_Name(String lastName) {
        return cardRepository.findByFirstName(lastName).orElseThrow();
    }

    public Card findCardByCard_cvv(String card_cvv) {
        return cardRepository.findByCardCvv(card_cvv).orElseThrow();
    }

    public Card findCardByCard_exp(String card_exp) {
        return cardRepository.findByCardExp(card_exp).orElseThrow();
    }

    public Card findCardByAccount_id(Integer account_id) {
        Account account = accountRepository.findByAccountId(account_id).orElseThrow();
        return cardRepository.findByAccountId(account).orElseThrow();
    }

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public Boolean deleteCard(Integer card_id) {
        return cardRepository.deleteByCardId(card_id);
    }
    
}
