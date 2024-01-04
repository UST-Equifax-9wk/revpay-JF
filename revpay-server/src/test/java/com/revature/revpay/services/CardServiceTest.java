package com.revature.revpay.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.Card;
import com.revature.revpay.repositories.AccountRepository;
import com.revature.revpay.repositories.CardRepository;

public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCardById() {
        Card card = new Card();
        card.setCardId(1);
        card.setCardNum("1234567890123456");
        card.setCardCvv("123");
        card.setCardExp("12/23");

        when(cardRepository.findByCardId(1)).thenReturn(Optional.of(card));

        Card result = cardService.findCardById(1);

        assertEquals(card, result);
    }

    @Test
    public void testFindCardByCard_number() {
        Card card = new Card();
        card.setCardId(1);
        card.setCardNum("1234567890123456");
        card.setCardCvv("123");
        card.setCardExp("12/23");

        when(cardRepository.findByCardNum("1234567890123456")).thenReturn(Optional.of(card));

        Card result = cardService.findCardByCard_number("1234567890123456");

        assertEquals(card, result);
    }

    @Test
    public void testFindCardByCard_cvv() {
        Card card = new Card();
        card.setCardId(1);
        card.setCardNum("1234567890123456");
        card.setCardCvv("123");
        card.setCardExp("12/23");

        when(cardRepository.findByCardCvv("123")).thenReturn(Optional.of(card));

        Card result = cardService.findCardByCard_cvv("123");

        assertEquals(card, result);
    }

    @Test
    public void testFindCardByCard_exp() {
        Card card = new Card();
        card.setCardId(1);
        card.setCardNum("1234567890123456");
        card.setCardCvv("123");
        card.setCardExp("12/23");

        when(cardRepository.findByCardExp("12/23")).thenReturn(Optional.of(card));

        Card result = cardService.findCardByCard_exp("12/23");

        assertEquals(card, result);
    }

    @Test
    public void testFindAllCardsByAccount_id() {
        Account account = new Account();
        account.setAccountId(1);

        Card card1 = new Card();
        card1.setCardId(1);
        card1.setCardNum("1234567890123456");
        card1.setCardCvv("123");
        card1.setCardExp("12/23");
        card1.setAccountId(account);

        Card card2 = new Card();
        card2.setCardId(2);
        card2.setCardNum("9876543210987654");
        card2.setCardCvv("456");
        card2.setCardExp("12/25");
        card2.setAccountId(account);

        Set<Card> cards = new HashSet<>();
        cards.add(card1);
        cards.add(card2);

        when(accountRepository.findByAccountId(1)).thenReturn(Optional.of(account));
        when(cardRepository.findAllCardsByAccountId(account)).thenReturn(cards);

        Set<Card> result = cardService.findAllCardsByAccount_id(1);

        assertEquals(cards, result);
    }

    @Test
    public void testAddCard() {
        Account account = new Account();
        account.setAccountId(1);

        Card card = new Card();
        card.setCardNum("1234567890123456");
        card.setCardCvv("123");
        card.setCardExp("12/23");

        when(accountRepository.findByAccountId(1)).thenReturn(Optional.of(account));
        when(cardRepository.save(card)).thenReturn(card);

        Card result = cardService.addCard(1, card);

        assertEquals(card, result);
        assertEquals(account, card.getAccountId());
    }

    @Test
    public void testUpdateCard() {
        Card card = new Card();
        card.setCardId(1);
        card.setCardNum("1234567890123456");
        card.setCardCvv("123");
        card.setCardExp("12/23");

        when(cardRepository.save(card)).thenReturn(card);

        Card result = cardService.updateCard(card);

        assertEquals(card, result);
    }

    @Test
    public void testDeleteCard() {
        when(cardRepository.deleteByCardId(1)).thenReturn(true);

        boolean result = cardService.deleteCard(1);

        assertTrue(result);
    }
}