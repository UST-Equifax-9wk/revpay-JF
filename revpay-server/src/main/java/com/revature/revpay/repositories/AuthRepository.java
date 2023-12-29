package com.revature.revpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revpay.entities.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {
    Optional<Auth> findByUsername(String username);
    void save(Optional<Auth> auth);
}
