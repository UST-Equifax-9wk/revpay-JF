package com.revature.revpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpay.entities.User;
import com.revature.revpay.exceptions.NoResultsException;
import com.revature.revpay.services.UserService;

/**
 * The UserController class handles HTTP requests related to User operations.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Retrieves a User by their user ID.
     *
     * @param user_id the ID of the User to retrieve
     * @return the User with the specified ID
     */
    @GetMapping("/user/id/{user_id}")
    @ResponseBody
    public User getUserById(@PathVariable Integer user_id) {
        return userService.findUserById(user_id);
    }
    
    /**
     * Retrieves a User by their username.
     *
     * @param username the username of the User to retrieve
     * @return the User with the specified username
     * @throws NoResultsException if no User is found with the specified username
     */
    @GetMapping("/user/username/{username}")
    public User getUserByUsername(@PathVariable String username) throws NoResultsException {
        return userService.findUserByUsername(username);
    }
    
    /**
     * Retrieves a User by their email.
     *
     * @param email the email of the User to retrieve
     * @return the User with the specified email
     * @throws NoResultsException
     */
    @GetMapping("/user/email/{email}")
    public User getUserByEmail(@PathVariable String email) throws NoResultsException {
        return userService.findUserByEmail(email);
    }
    
    /**
     * Retrieves a User by their phone number.
     *
     * @param phone_number the phone number of the User to retrieve
     * @return the User with the specified phone number
     * @throws NoResultsException
     */
    @GetMapping(path="/user/phone/{phone_number}")
    public User getUserByPhone_number(@PathVariable String phone_number) throws NoResultsException {
        return userService.findUserByPhone_number(phone_number);
    }
    
    /**
     * Adds a new User.
     *
     * @param user the User to add
     * @return the added User
     */
    @PostMapping(path="/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    
    /**
     * Updates an existing User.
     *
     * @param user the User to update
     * @return the updated User
     */
    @PutMapping(path="/user")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    
    /**
     * Deletes a User by their user ID.
     *
     * @param user_id the ID of the User to delete
     */
    @DeleteMapping(path="/user/{user_id}")
    public void deleteUser(@PathVariable Integer user_id) {
        User user = userService.findUserById(user_id);
        userService.deleteUser(user);
    }

    /**
     * Handles the NoResultsException and returns an error message.
     *
     * @param e the NoResultsException to handle
     * @return the error message
     */
    @ExceptionHandler(NoResultsException.class)
    public String handleNoResultsException(NoResultsException e) {
        return e.getMessage();
    }
}
