package com.revature.revpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.User;
import com.revature.revpay.exceptions.NoResultsException;
import com.revature.revpay.repositories.UserRepository;

/**
 * This class represents a service for managing user-related operations.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class UserService {
    private final UserRepository userRepository;

    /**
     * Constructs a new UserService with the specified UserRepository.
     * 
     * @param userRepository the UserRepository to be used for data access
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by their ID.
     * 
     * @param user_id the ID of the user to retrieve
     * @return the User object with the specified ID
     */
    public User findUserById(Integer user_id) {
        return userRepository.findByUserId(user_id).get();
    }

    /**
     * Retrieves a user by their username.
     * 
     * @param username the username of the user to retrieve
     * @return the User object with the specified username
     * @throws NoResultsException if no user is found with the specified username
     */
    public User findUserByUsername(String username) throws NoResultsException{
        return userRepository.findByUsername(username).orElseThrow();
    }

    /**
     * Retrieves a user by their email.
     * 
     * @param email the email of the user to retrieve
     * @return the User object with the specified email
     * @throws NoResultsException if no user is found with the specified email
     */
    public User findUserByEmail(String email) throws NoResultsException {
        return userRepository.findByEmail(email).orElseThrow();
    }

    /**
     * Retrieves a user by their phone number.
     * 
     * @param phone_number the phone number of the user to retrieve
     * @return the User object with the specified phone number
     * @throws NoResultsException if no user is found with the specified phone number
     */
    public User findUserByPhone_number(String phone_number) throws NoResultsException {
        return userRepository.findByPhoneNumber(phone_number).orElseThrow();
    }

    /**
     * Checks if a user exists with the specified username.
     * 
     * @param username the username to check
     * @return true if a user exists with the specified username, false otherwise
     */
    public Boolean CheckByUserName(String username) {
        return userRepository.existsByUsername(username);
    }
    
    /**
     * Adds a new user.
     * 
     * @param user the User object to add
     * @return the added User object
     */
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Updates an existing user.
     * 
     * @param user the User object to update
     * @return the updated User object
     */
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user.
     * 
     * @param user the User object to delete
     */
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
