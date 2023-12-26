package com.revature.revpay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(indexes = {@Index(columnList = "user_id", unique = true)})
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "username", nullable = false, length = 60)
    private String username;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "passwordHash", nullable = false, length = 60)
    private String passwordHash;

    @Column(name = "phone_number", nullable = true, length = 10)
    private String phone_number;

    //given both email and phone number are optional, we need to make sure that the user has at least one of them
    //if they have neither, then they cannot be contacted
    //if they have both, then they can be contacted by either
    public User(String username, String email, String password,  String phone_number) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }
  
    //if they have only one, then they can only be contacted by that one
    public User(String username, String contact, String password) {
        this.username = username;
        if(contact.contains("@"))
            this.email = contact;
        else
            this.phone_number = contact;
        this.password = password;
    }
    
    //empty constructor
    public User() {
        super();
    }
    
    // Setters and Getters
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}

    






