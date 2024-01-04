package com.revature.revpay.services;

import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.User;
import com.revature.revpay.repositories.AccountRepository;

/**
 * The AccountService class provides methods for managing accounts.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param account_id The ID of the account to retrieve.
     * @return The account with the specified ID.
     * @throws NoSuchElementException if no account with the specified ID exists.
     */
    public Account findAccountById(Integer account_id) {
        return accountRepository.findByAccountId(account_id).orElseThrow();
    }

    /**
     * Retrieves all accounts associated with a user.
     *
     * @param user_id The ID of the user.
     * @return A set of accounts associated with the user.
     */
    public Set<Account> findAccountsByUserId(Integer user_id) {
        return accountRepository.findAllAccountsByUserId(user_id);
    }

    /**
     * Retrieves an account by its account type.
     *
     * @param account_type The account type.
     * @return The account with the specified account type.
     * @throws NoSuchElementException if no account with the specified account type exists.
     */
    public Account findAccountByAccountType(Boolean account_type) {
        return accountRepository.findByAccountType(account_type).orElseThrow();
    }

    /**
     * Adds a new account for a user.
     *
     * @param id      The ID of the user.
     * @param account The account to add.
     * @return The added account.
     */
    public Account addAccount(Integer id, Account account) {
        User user = userService.findUserById(id);
        account.setUserId(user);
        return accountRepository.save(account);
    }

    /**
     * Updates an existing account.
     *
     * @param id      The ID of the account to update.
     * @param account The updated account information.
     * @return The updated account.
     * @throws NoSuchElementException if no account with the specified ID exists.
     */
    public Account updateAccount(Integer id, Account account) {
        return accountRepository.findByAccountId(id).map(a -> {
            a.setAccountType(account.getAccountType());
            a.setAccountName(account.getAccountName());
            a.setBalance(account.getBalance());
            return accountRepository.save(a);
        }).orElseThrow();
    }

    /**
     * Deletes an account.
     *
     * @param id The ID of the account to delete.
     * @return true if the account was successfully deleted, false otherwise.
     */
    public Boolean deleteAccount(int id) {
        return accountRepository.deleteByAccountId(id);
    }
}
