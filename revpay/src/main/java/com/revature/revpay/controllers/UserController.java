package com.revature.revpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpay.entities.User;
import com.revature.revpay.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/{user_id}")
    public User getUserById(@PathVariable Integer user_id) {
        return userService.findUserById(user_id);
    }
    
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }
    
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }
    
    @GetMapping("/{phone_number}")
    public User getUserByPhone_number(@PathVariable String phone_number) {
        return userService.findUserByPhone_number(phone_number);
    }
    
    @PostMapping
    public User addUser(User user) {
        return userService.addUser(user);
    }
    
    @PutMapping
    public User updateUser(User user) {
        return userService.updateUser(user);
    }
    
    @DeleteMapping
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }
}
