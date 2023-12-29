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

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/user/id/{user_id}")
    @ResponseBody
    public User getUserById(@PathVariable Integer user_id) {
        return userService.findUserById(user_id);
    }
    
    @GetMapping("/user/username/{username}")
    public User getUserByUsername(@PathVariable String username)throws NoResultsException {
        return userService.findUserByUsername(username);
    }
    
    @GetMapping("/user/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }
    
    @GetMapping(path="/user/phone/{phone_number}")
    public User getUserByPhone_number(@PathVariable String phone_number) {
        return userService.findUserByPhone_number(phone_number);
    }
    
    @PostMapping(path="/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    
    @PutMapping(path="/user")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    
    @DeleteMapping(path="/user/{user_id}")
    public void deleteUser(@PathVariable Integer user_id) {
        User user = userService.findUserById(user_id);
        userService.deleteUser(user);
    }

    @ExceptionHandler(NoResultsException.class)
    public String handleNoResultsException(NoResultsException e) {
        return e.getMessage();
    }
}
