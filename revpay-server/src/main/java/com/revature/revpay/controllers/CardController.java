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

/**
 * The CardController class handles HTTP requests related to Card operations.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CardController {
    private final CardService cardService;

    /**
     * Constructs a new CardController with the given CardService.
     * 
     * @param cardService the CardService to be used
     */
    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Retrieves a Card by its ID.
     * 
     * @param card_id the ID of the Card to retrieve
     * @return the Card with the specified ID
     */
    @GetMapping("/card/{card_id}")
    public Card findCardById(@PathVariable("card_id") Integer card_id) {
        return cardService.findCardById(card_id);
    }

    /**
     * Retrieves all Cards associated with an account.
     * 
     * @param account_id the ID of the account
     * @return a Set of Cards associated with the specified account
     */
    @GetMapping("/cards/{account_id}")
    public Set<Card> findCardByAccount_id(@PathVariable("account_id") Integer account_id) {
        return cardService.findAllCardsByAccount_id(account_id);
    }

    /**
     * Adds a new Card to an account.
     * 
     * @param account_id the ID of the account to add the Card to
     * @param card the Card to be added
     * @return the added Card
     */
    @PostMapping("/card/{account_id}")
    public Card addCard(@PathVariable Integer account_id, @RequestBody Card card) {
        return cardService.addCard(account_id, card);
    }

    /**
     * Updates an existing Card.
     * 
     * @param card the Card to be updated
     * @return the updated Card
     */
    @PutMapping("/card")
    public Card updateCard(Card card) {
        return cardService.updateCard(card);
    }

    /**
     * Deletes a Card by its ID.
     * 
     * @param card_id the ID of the Card to delete
     * @return true if the Card was successfully deleted, false otherwise
     */
    @DeleteMapping("/card/{card_id}")
    public Boolean deleteCard(@PathVariable("card_id") Integer card_id) {
        return cardService.deleteCard(card_id);
    }
}
