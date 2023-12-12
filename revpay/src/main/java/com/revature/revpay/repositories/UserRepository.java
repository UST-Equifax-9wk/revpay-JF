package com.revature.revpay.repositories;

import com.revature.revpay.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    User findByCreditCardNumber(String creditCardNumber);
}
