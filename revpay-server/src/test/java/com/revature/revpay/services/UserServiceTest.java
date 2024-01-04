package com.revature.revpay.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.revpay.entities.User;
import com.revature.revpay.exceptions.NoResultsException;
import com.revature.revpay.repositories.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        when(userRepository.findByUserId(1)).thenReturn(Optional.of(user));

        User result = userService.findUserById(1);

        assertEquals(user, result);
    }

    @Test
    public void testFindUserByUsername() throws NoResultsException {
        User user = new User();
        user.setUserId(1);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(user));

        User result = userService.findUserByUsername("john_doe");

        assertEquals(user, result);
    }

    @Test
    public void testFindUserByEmail() throws NoResultsException {
        User user = new User();
        user.setUserId(1);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        User result = userService.findUserByEmail("john.doe@example.com");

        assertEquals(user, result);
    }

    @Test
    public void testFindUserByPhone_number() throws NoResultsException {
        User user = new User();
        user.setUserId(1);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        when(userRepository.findByPhoneNumber("1234567890")).thenReturn(Optional.of(user));

        User result = userService.findUserByPhone_number("1234567890");

        assertEquals(user, result);
    }

    @Test
    public void testCheckByUserName() {
        when(userRepository.existsByUsername("john_doe")).thenReturn(true);

        boolean result = userService.CheckByUserName("john_doe");

        assertTrue(result);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.addUser(user);

        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(user);

        assertEquals(user, result);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        userService.deleteUser(user);

        verify(userRepository, times(1)).delete(user);
    }
}