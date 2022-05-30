package com.bookingshine;

import com.google.firebase.database.Exclude;

public class BusinessSignIn {

    @Exclude

    private String email;
    private String password;

    public BusinessSignIn(){}

    public BusinessSignIn(String email, String password) {
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
