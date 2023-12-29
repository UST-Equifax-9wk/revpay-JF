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


    @PostMapping(path="/register")
    public User register(@RequestBody NewUserDto nUser, HttpServletResponse response) throws UsernameUnavailableException{
        return authService.registerUser(nUser);
    }

    @ExceptionHandler(UsernameUnavailableException.class)
    public String handleUsernameUnavailableException(UsernameUnavailableException e) {
        return e.getMessage();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException e) {
        return e.getMessage();
    }
}
