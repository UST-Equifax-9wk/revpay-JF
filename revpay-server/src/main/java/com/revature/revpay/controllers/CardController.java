package com.revature.revpay.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpay.entities.Card;
import com.revature.revpay.services.CardService;



@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card/{card_id}")
    public Card findCardById(@PathVariable("card_id") Integer card_id) {
        return cardService.findCardById(card_id);
    }

    @GetMapping("/cards/{account_id}")
    public Set<Card> findCardByAccount_id(@PathVariable("account_id") Integer account_id) {
        return cardService.findAllCardsByAccount_id(account_id);
    }


    @PostMapping("/card/{account_id}")
    public Card addCard(@PathVariable Integer account_id, @RequestBody Card card) {
        return cardService.addCard(account_id, card);
    }

    @PutMapping("/card")
    public Card updateCard(Card card) {
        return cardService.updateCard(card);
    }

    @DeleteMapping("/card/{card_id}")
    public Boolean deleteCard(@PathVariable("card_id") Integer card_id) {
        return cardService.deleteCard(card_id);
    }


    
}
