package com.revature.revpay.controllers;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.revature.revpay.entities.User;
import com.revature.revpay.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Mock UserService methods
        User user = new User();
        user.setUserId(1);
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");
        user.setPhoneNumber("1234567890");

        when(userService.findUserById(1)).thenReturn(user);
        when(userService.findUserByUsername("testuser")).thenReturn(user);
        when(userService.findUserByEmail("testuser@example.com")).thenReturn(user);
        when(userService.findUserByPhone_number("1234567890")).thenReturn(user);
        when(userService.addUser(user)).thenReturn(user);
        when(userService.updateUser(user)).thenReturn(user);
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/testuser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/testuser@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testGetUserByPhoneNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/1234567890")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");
        user.setPhoneNumber("1234567890");

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");
        user.setPhoneNumber("1234567890");

        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");
        user.setPhoneNumber("1234567890");

        mockMvc.perform(MockMvcRequestBuilders.delete("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the deleteUser method was called
        Mockito.verify(userService, Mockito.times(1)).deleteUser(user);
    }
}