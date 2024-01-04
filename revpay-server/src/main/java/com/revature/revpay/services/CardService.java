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

/**
 * This class represents the service layer for Card-related operations.
 * It provides methods to interact with the CardRepository and AccountRepository
 * to perform CRUD operations on Card entities.
 */
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

    /**
     * Retrieves a Card entity by its ID.
     *
     * @param card_id The ID of the Card to retrieve.
     * @return The Card entity with the specified ID.
     * @throws java.util.NoSuchElementException if no Card with the specified ID exists.
     */
    public Card findCardById(Integer card_id) {
        return cardRepository.findByCardId(card_id).orElseThrow();
    }

    /**
     * Retrieves a Card entity by its card number.
     *
     * @param card_number The card number of the Card to retrieve.
     * @return The Card entity with the specified card number.
     * @throws java.util.NoSuchElementException if no Card with the specified card number exists.
     */
    public Card findCardByCard_number(String card_number) {
        return cardRepository.findByCardNum(card_number).orElseThrow();
    }

    /**
     * Retrieves a Card entity by its card CVV.
     *
     * @param card_cvv The card CVV of the Card to retrieve.
     * @return The Card entity with the specified card CVV.
     * @throws java.util.NoSuchElementException if no Card with the specified card CVV exists.
     */
    public Card findCardByCard_cvv(String card_cvv) {
        return cardRepository.findByCardCvv(card_cvv).orElseThrow();
    }

    /**
     * Retrieves a Card entity by its card expiration date.
     *
     * @param card_exp The card expiration date of the Card to retrieve.
     * @return The Card entity with the specified card expiration date.
     * @throws java.util.NoSuchElementException if no Card with the specified card expiration date exists.
     */
    public Card findCardByCard_exp(String card_exp) {
        return cardRepository.findByCardExp(card_exp).orElseThrow();
    }

    /**
     * Retrieves all Card entities associated with the specified account ID.
     *
     * @param account_id The ID of the account to retrieve the Card entities for.
     * @return A set of Card entities associated with the specified account ID.
     * @throws java.util.NoSuchElementException if no Account with the specified ID exists.
     */
    public Set<Card> findAllCardsByAccount_id(Integer account_id) {
        Account account = accountRepository.findByAccountId(account_id).orElseThrow();
        return cardRepository.findAllCardsByAccountId(account);
    }

    /**
     * Adds a new Card entity to the specified account.
     *
     * @param id   The ID of the account to add the Card to.
     * @param card The Card entity to add.
     * @return The added Card entity.
     */
    public Card addCard(Integer id, Card card) {
        Account account = accountRepository.findByAccountId(id).get();
        card.setAccountId(account);
        return cardRepository.save(card);
    }

    /**
     * Updates an existing Card entity.
     *
     * @param card The Card entity to update.
     * @return The updated Card entity.
     */
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    /**
     * Deletes a Card entity by its ID.
     *
     * @param card_id The ID of the Card to delete.
     * @return true if the Card was successfully deleted, false otherwise.
     */
    public Boolean deleteCard(Integer card_id) {
        return cardRepository.deleteByCardId(card_id);
    }
}
