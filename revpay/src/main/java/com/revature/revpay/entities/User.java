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
    public User(String username, String email, String password, String passwordHash, String phone_number) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordHash = passwordHash;
        this.phone_number = phone_number;
    }
  //if they have only one, then they can only be contacted by that one
    public User(String username, String contact, String password, String passwordHash) {
        this.username = username;
        if(contact.contains("@"))
            this.email = contact;
        else
            this.phone_number = contact;
        this.password = password;
        this.passwordHash = passwordHash;
    }





}
