package com.revature.revpay.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Account;
import com.revature.revpay.entities.User;
import com.revature.revpay.repositories.AccountRepository;

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

    public Account findAccountById(Integer account_id) {
        return accountRepository.findByAccountId(account_id).orElseThrow();
    }

    public Set<Account> findAccountsByUserId(Integer user_id) {
        return accountRepository.findAllAccountsByUserId(user_id);
    }

    public Account findAccountByAccount_type(Boolean account_type) {
        return accountRepository.findByAccountType(account_type).orElseThrow();
    }

    public Account addAccount(Integer id, Account account) {
        User user = userService.findUserById(id);
        account.setUserId(user);
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public Boolean deleteAccount(int id) {
        return accountRepository.deleteByAccountId(id);
    }


}
