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

/**
 * The AccountController class handles HTTP requests related to accounts.
 * It provides endpoints for retrieving, creating, updating, and deleting accounts.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AccountController {
    private final AccountService accountService;

    /**
     * Constructs an AccountController object with the specified AccountService.
     * 
     * @param accountService the AccountService used to perform account-related operations
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Retrieves an account by its ID.
     * 
     * @param id the ID of the account to retrieve
     * @return the account with the specified ID, or null if not found
     */
    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable int id) {
        return accountService.findAccountById(id);
    }

    /**
     * Retrieves all accounts associated with a user.
     * 
     * @param id the ID of the user
     * @return a set of accounts associated with the user, or an empty set if none found
     */
    @GetMapping("/account/user/{id}")
    public Set<Account> getAccountsByUserId(@PathVariable Integer id) {
        return accountService.findAccountsByUserId(id);
    }

    /**
     * Creates a new account for a user.
     * 
     * @param id      the ID of the user
     * @param account the account to create
     * @return the created account
     */
    @PostMapping("/account/{id}")
    public Account createAccount(@PathVariable Integer id, @RequestBody Account account) {
        return accountService.addAccount(id, account);
    }

    /**
     * Updates an existing account.
     * 
     * @param id      the ID of the account to update
     * @param account the updated account information
     * @return the updated account
     */
    @PutMapping("/account/{id}")
    public Account updateAccount(@PathVariable Integer id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    /**
     * Deletes an account by its ID.
     * 
     * @param id the ID of the account to delete
     * @return true if the account was successfully deleted, false otherwise
     */
    @DeleteMapping("/account/{id}")
    public Boolean deleteAccount(@PathVariable int id) {
        return accountService.deleteAccount(id);
    }

    // Add more controller methods here

}
    
