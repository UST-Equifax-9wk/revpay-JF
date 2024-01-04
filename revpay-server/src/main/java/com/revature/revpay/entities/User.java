package com.revature.revpay.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a User entity in the application.
 */
@Entity
@Table(name = "users", indexes = {@Index(columnList = "userId", unique = true)})
public class User {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "username", nullable = false, length = 60, unique = true)
    private String username;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "phoneNumber", nullable = true, length = 10)
    private String phoneNumber;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user")
    private Set<Account> accounts;

    /**
     * Constructs a User object with the given username, email, phone number, and accounts.
     * 
     * @param username The username of the user.
     * @param email The email of the user.
     * @param phone_number The phone number of the user.
     * @param accounts The set of accounts associated with the user.
     */
    public User(String username, String email, String phone_number, Set<Account> accounts) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phone_number;
        this.accounts = accounts;
    }

    /**
     * Constructs a User object with the given username and contact information.
     * The contact information can be either an email or a phone number.
     * 
     * @param username The username of the user.
     * @param contact The contact information (email or phone number) of the user.
     */
    public User(String username, String contact) {
        this.username = username;
        if(contact.contains("@"))
            this.email = contact;
        else
            this.phoneNumber = contact;
    }
    
    /**
     * Constructs an empty User object.
     */
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

}

    






