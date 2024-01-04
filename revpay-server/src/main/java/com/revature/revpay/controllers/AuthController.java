package com.revature.revpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpay.dto.NewUserDto;
import com.revature.revpay.entities.Auth;
import com.revature.revpay.entities.User;
import com.revature.revpay.exceptions.AccessDeniedException;
import com.revature.revpay.exceptions.NoResultsException;
import com.revature.revpay.exceptions.UsernameUnavailableException;
import com.revature.revpay.services.AuthService;
import com.revature.revpay.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


/**
 * This class represents the controller for handling authentication-related requests.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    /**
     * Handles the login request and returns the user if the authentication is successful.
     * Throws an AccessDeniedException if the authentication fails.
     * 
     * @param auth The authentication object containing the username and password.
     * @param response The HttpServletResponse object used to set the cookie.
     * @return The User object representing the logged-in user.
     * @throws AccessDeniedException If the authentication fails.
     */
    @PostMapping(path="/login")
    public User Login(@RequestBody Auth auth, HttpServletResponse response) throws AccessDeniedException{
        // check if the user is logged in
        if (authService.login(auth)) {
            try{
                // if the user is logged in, create a cookie with the username
                Cookie cookie = new Cookie("username", auth.getUsername());
                // set the cookie to expire in 7 days
                cookie.setMaxAge(60*60*24*7);
                cookie.setPath("/");
                response.addCookie(cookie);
                // return the user
                return userService.findUserByUsername(auth.getUsername());
            } catch (NoResultsException e) { // if the user is not found
                throw new AccessDeniedException("Invalid Credentials");
            }
        }
        else {
            throw new AccessDeniedException("Invalid Credentials");
        }
    }

    /**
     * Handles the registration request and returns the registered user.
     * Throws a UsernameUnavailableException if the username is already taken.
     * 
     * @param nUser The NewUserDto object containing the user's information.
     * @param response The HttpServletResponse object used to set the cookie.
     * @return The User object representing the registered user.
     * @throws UsernameUnavailableException If the username is already taken.
     */
    @PostMapping(path="/register")
    public User register(@RequestBody NewUserDto nUser, HttpServletResponse response) throws UsernameUnavailableException{
        return authService.registerUser(nUser);
    }

    /**
     * Handles the UsernameUnavailableException and returns the error message.
     * 
     * @param e The UsernameUnavailableException object.
     * @return The error message.
     */
    @ExceptionHandler(UsernameUnavailableException.class)
    public String handleUsernameUnavailableException(UsernameUnavailableException e) {
        return e.getMessage();
    }

    /**
     * Handles the AccessDeniedException and returns the error message.
     * 
     * @param e The AccessDeniedException object.
     * @return The error message.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException e) {
        return e.getMessage();
    }
}
