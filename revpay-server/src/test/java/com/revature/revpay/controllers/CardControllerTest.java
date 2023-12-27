package com.revature.revpay.controllers;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.revature.revpay.entities.Card;
import com.revature.revpay.services.CardService;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @BeforeEach
    public void setUp() {
        // Mock CardService methods
        Card card = new Card();
        card.setCardId(1);
        card.setCardNumber("1234567890123456");
        card.setCardHolderName("John Doe");
        card.setExpirationDate("12/23");
        card.setCvv("123");

        when(cardService.findCardById(1)).thenReturn(card);
        when(cardService.addCard(card)).thenReturn(card);
        when(cardService.updateCard(card)).thenReturn(card);
        when(cardService.deleteCard(card.get_id())).thenReturn(true);
    }

    @Test
    public void testGetCardById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cards/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardNumber").value("1234567890123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardHolderName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.expirationDate").value("12/23"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cvv").value("123"));
    }

    @Test
    public void testAddCard() throws Exception {
        Card card = new Card();
        card.setCardNumber("1234567890123456");
        card.setCardHolderName("John Doe");
        card.setExpirationDate("12/23");
        card.setCvv("123");

        mockMvc.perform(MockMvcRequestBuilders.post("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(card.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardNumber").value("1234567890123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardHolderName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.expirationDate").value("12/23"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cvv").value("123"));
    }

    @Test
    public void testUpdateCard() throws Exception {
        Card card = new Card();
        card.setCardId(1);
        card.setCardNumber("1234567890123456");
        card.setCardHolderName("John Doe");
        card.setExpirationDate("12/23");
        card.setCvv("123");

        mockMvc.perform(MockMvcRequestBuilders.put("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(card.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardNumber").value("1234567890123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardHolderName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.expirationDate").value("12/23"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cvv").value("123"));
    }

    @Test
    public void testDeleteCard() throws Exception {
        Card card = new Card();
        card.setCardId(1);
        card.setCardNumber("1234567890123456");
        card.setCardHolderName("John Doe");
        card.setExpirationDate("12/23");
        card.setCvv("123");

        mockMvc.perform(MockMvcRequestBuilders.delete("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(card.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the deleteCard method was called
        verify(cardService, times(1)).deleteCard(card.get_id());
    }
}