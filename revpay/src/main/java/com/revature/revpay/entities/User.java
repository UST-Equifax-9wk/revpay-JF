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

    @Column(name = "phone_number", nullable = true, length = 10)
    private String phone_number;

    @Column(name = "business", nullable = false)
    private Boolean business = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Credit> users;

    public User(Integer user_id, String username, String email, String password, String phone_number) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

    public User(Integer user_id, Boolean business, String username, String email, String password, String phone_number) {
        this.user_id = user_id;
        this.business = business;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

}
