package com.revature.revpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpay.entities.Card;
import com.revature.revpay.services.CardService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        super();
        this.cardService = cardService;
    }

    @GetMapping("/card/{card_id}")
    public Card findCardById(@PathVariable("card_id") Integer card_id) {
        return cardService.findCardById(card_id);
    }

    @GetMapping("/card/{card_number}")
    public Card findCardByCard_number(@PathVariable("card_number") String card_number) {
        return cardService.findCardByCard_number(card_number);
    }

    @GetMapping("/card/{card_cvv}")
    public Card findCardByCard_cvv(@PathVariable("card_cvv") String card_cvv) {
        return cardService.findCardByCard_cvv(card_cvv);
    }

    @GetMapping("/card/{card_exp}")
    public Card findCardByCard_exp(@PathVariable("card_exp") String card_exp) {
        return cardService.findCardByCard_exp(card_exp);
    }

    @GetMapping("/card/{account_id}")
    public Card findCardByAccount_id(@PathVariable("account_id") Integer account_id) {
        return cardService.findCardByAccount_id(account_id);
    }

    @PostMapping("/card")
    public Card addCard(Card card) {
        return cardService.addCard(card);
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
