package com.revature.revpay.controllers;

import static org.mockito.ArgumentMatchers.*;
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

import com.revature.revpay.entities.User;
import com.revature.revpay.exceptions.NoResultsException;
import com.revature.revpay.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() throws NoResultsException {
        // Mock UserService methods
        User user = new User();
        user.setUserId(1);
        user.setUserName("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        when(userService.findUserById(1)).thenReturn(user);
        when(userService.findUserByUsername("john_doe")).thenReturn(user);
        when(userService.findUserByEmail("john.doe@example.com")).thenReturn(user);
        when(userService.findUserByPhone_number("1234567890")).thenReturn(user);
        when(userService.addUser(user)).thenReturn(user);
        when(userService.updateUser(user)).thenReturn(user);
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john_doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/john_doe")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john_doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/john.doe@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john_doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testGetUserByPhone_number() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1234567890")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john_doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setUserName("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john_doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setUserName("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john_doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the deleteUser method was called
        verify(userService, times(1)).deleteUser(any(User.class));
    }

    @Test
    public void testHandleNoResultsException() throws Exception {
        when(userService.findUserByUsername("non_existing_user")).thenThrow(new NoResultsException("User not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/non_existing_user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User not found"));
    }
}