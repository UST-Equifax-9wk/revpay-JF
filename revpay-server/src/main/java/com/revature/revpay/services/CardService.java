package com.revature.revpay.services;

import java.util.Set;

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
    private final AccountService accountService;

    @Autowired
    public CardService(CardRepository cardRepository, AccountRepository accountRepository, AccountService accountService) {
        this.cardRepository = cardRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;

    }


    public Card findCardById(Integer card_id) {
        return cardRepository.findByCardId(card_id).orElseThrow();
    }

    public Card findCardByCard_number(String card_number) {
        return cardRepository.findByCardNum(card_number).orElseThrow();
    }

    public Card findCardByCard_cvv(String card_cvv) {
        return cardRepository.findByCardCvv(card_cvv).orElseThrow();
    }

    public Card findCardByCard_exp(String card_exp) {
        return cardRepository.findByCardExp(card_exp).orElseThrow();
    }

    public Set<Card> findAllCardsByAccount_id(Integer account_id) {
        Account account = accountRepository.findByAccountId(account_id).orElseThrow();
        return cardRepository.findAllCardsByAccountId(account);
    }

    public Card addCard(Integer id, Card card) {
        Account account = accountRepository.findByAccountId(id).get();
        card.setAccountId(account);
        return cardRepository.save(card);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public Boolean deleteCard(Integer card_id) {
        return cardRepository.deleteByCardId(card_id);
    }
    
}
