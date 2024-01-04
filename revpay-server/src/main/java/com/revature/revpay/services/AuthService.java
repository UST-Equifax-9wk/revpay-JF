/**
 * This class represents the authentication service that handles user registration and login.
 */
package com.revature.revpay.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.revpay.dto.NewUserDto;
import com.revature.revpay.entities.Auth;
import com.revature.revpay.entities.User;
import com.revature.revpay.exceptions.UsernameUnavailableException;
import com.revature.revpay.repositories.AuthRepository;

import jakarta.transaction.Transactional;

@Service("authService")
public class AuthService {
    private UserService userService;
    private AuthRepository authRepository;

    @Autowired
    public AuthService(UserService userServices, AuthRepository authRepository) {
        this.userService = userServices;
        this.authRepository = authRepository;
    }

    /**
     * Registers a new user with the provided user information and authentication details.
     * 
     * @param nUser the new user information
     * @return the registered user
     * @throws UsernameUnavailableException if the username is already taken
     */
    @Transactional
    public User registerUser(NewUserDto nUser) throws UsernameUnavailableException {
        // check if username is available
        if (!this.userService.CheckByUserName(nUser.getUser().getUsername())){
            // setting the password to the hashed password
            nUser.getAuth().setPassword(this.hash(nUser.getAuth().getPassword()));
            // saves the user with the hashed password to the auth repo
            this.authRepository.save(nUser.getAuth());
            // returns the user when it saved to the user repo
            return this.userService.addUser(nUser.getUser());
        }
        throw new UsernameUnavailableException("Username is unavailable");
    }

    /**
     * Authenticates the user with the provided authentication details.
     * 
     * @param auth the authentication details
     * @return true if the authentication is successful, false otherwise
     */
    public boolean login(Auth auth) {
        // finds the user by the username in repo
        Optional<Auth> loginUser = this.authRepository.findByUsername(auth.getUsername());
        // checks if the user is present and if the password is correct
        if (loginUser.isPresent() && this.authenticate(auth.getPassword(), loginUser.get().getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * Hashes the provided password using BCrypt.
     * 
     * @param password the password to be hashed
     * @return the hashed password
     */
    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    /**
     * Authenticates the provided password against the provided hash using BCrypt.
     * 
     * @param password the password to be authenticated
     * @param hash the hash to be compared against
     * @return true if the password matches the hash, false otherwise
     */
    public boolean authenticate(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}

