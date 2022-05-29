package com.bookingshine;

import com.google.firebase.database.Exclude;

public class BusinessUsers {

    @Exclude

    private String email;
    private String password;

    public BusinessUsers(){}

    public BusinessUsers(String email, String password) {
        this.email = email;
        this.password = password;
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
}
