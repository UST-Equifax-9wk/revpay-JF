package com.revature.revpay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents an authentication entity.
 */
@Entity
@Table(name="auth")
public class Auth {
    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    /**
     * Constructs a new Auth object with the specified username and password.
     * 
     * @param username the username
     * @param password the password
     */
    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructs a new empty Auth object.
     */
    public Auth() {
    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username.
     * 
     * @param s the username to set
     */
    public void setUsername(String s) {
        this.username = s;
    }

    /**
     * Sets the password.
     * 
     * @param s the password to set
     */
    public void setPassword(String s) {
        this.password = s;
    }

    /**
     * Returns a string representation of the Auth object.
     * 
     * @return a string representation of the Auth object
     */
    @Override
    public String toString() {
        return "Auth{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
