package com.revature.revpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.User;
import com.revature.revpay.repositories.UserRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Integer user_id) {
        return userRepository.findByUser_id(user_id).orElseThrow();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User findUserByPhone_number(String phone_number) {
        return userRepository.findByPhone_number(phone_number).orElseThrow();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
