package com.bookingshine;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class ScheduleUser implements Serializable {

    @Exclude
    private String key;
    private String NameU;
    private String LNameU;
    private String EmailU;
    private String DateU;
    public ScheduleUser(){}
    public ScheduleUser(String nameU, String LNameU, String emailU, String dateU) {
        this.NameU = nameU;
        this.LNameU = LNameU;
        this.EmailU = emailU;
        this.DateU = dateU;
    }

    public String getNameU() {
        return NameU;
    }

    public void setNameU(String nameU) {
        this.NameU = nameU;
    }

    public String getLNameU() {
        return LNameU;
    }

    public void setLNameU(String LNameU) {
        this.LNameU = LNameU;
    }

    public String getEmailU() {
        return EmailU;
    }

    public void setEmailU(String emailU) {
        this.EmailU = emailU;
    }

    public String getDateU() {
        return DateU;
    }

    public void setDateU(String dateU) {
        this.DateU = dateU;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
