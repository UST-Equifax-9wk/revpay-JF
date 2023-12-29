package com.revature.revpay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="auth")
public class Auth {
    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Auth() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String s) {
        this.username = s;
    }

    public void setPassword(String s) {
        this.password = s;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
