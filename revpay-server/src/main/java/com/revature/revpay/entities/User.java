package com.revature.revpay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", indexes = {@Index(columnList = "userId", unique = true)})
public class User {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "userName", nullable = false, length = 60)
    private String userName;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "passwordHash", nullable = false, length = 60)
    private String passwordHash;

    @Column(name = "phoneNumber", nullable = true, length = 10)
    private String phoneNumber;

    //given both email and phone number are optional, we need to make sure that the user has at least one of them
    //if they have neither, then they cannot be contacted
    //if they have both, then they can be contacted by either
    public User(String username, String email, String password,  String phone_number) {
        this.userName = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phone_number;
    }

    //if they have only one, then they can only be contacted by that one
    public User(String username, String contact, String password) {
        this.userName = username;
        if(contact.contains("@"))
            this.email = contact;
        else
            this.phoneNumber = contact;
        this.password = password;
    }
    
    //empty constructor
    public User() {
        super();
    }
    
    // Setters and Getters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }
}

    






