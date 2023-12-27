package com.revature.revpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Account;
import com.revature.revpay.repositories.AccountRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findAccountById(Integer account_id) {
        return accountRepository.findByAccountId(account_id).orElseThrow();
    }

    public Account findAccountByUser_id(Integer user_id) {
        return accountRepository.findByUser_UserId(user_id).orElseThrow();
    }

    public Account findAccountByAccount_type(Boolean account_type) {
        return accountRepository.findByAccountType(account_type).orElseThrow();
    }

    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public Boolean deleteAccount(int id) {
        return accountRepository.deleteByAccountId(id);
    }


}
