package com.revature.revpay.dto;

import com.revature.revpay.entities.Auth;
import com.revature.revpay.entities.User;


public class NewUserDto {
    private User user;
    private Auth auth;
    
    public NewUserDto(User user, Auth auth) {
        this.user = user;
        this.auth = auth;
    }

    public NewUserDto() {
    }

    public User getUser() {
        return user;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "NewUserDto{" +
                "user=" + user +
                ", auth=" + auth +
                '}';
    }
}
