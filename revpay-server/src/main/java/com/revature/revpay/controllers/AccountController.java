package com.revature.revpay.controllers;


import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpay.entities.Account;
import com.revature.revpay.services.AccountService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable int id) {
        return accountService.findAccountById(id);
    }

    @GetMapping("/account/user/{id}")
    public Set<Account> getAccountsByUserId(@PathVariable Integer id) {
        return accountService.findAccountsByUserId(id);
    }

    @PostMapping("/account/{id}")
    public Account createAccount(@PathVariable Integer id, @RequestBody Account account) {
        return accountService.addAccount(id, account);
    }

    @PutMapping("/account/{id}")
    public Account updateAccount(@PathVariable Integer id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/account/{id}")
    public Boolean deleteAccount(@PathVariable int id) {
        return accountService.deleteAccount(id);
    }

    // Add more controller methods here

}
    
