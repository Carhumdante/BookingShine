package com.bookingshine;

public class BusinessReschedule {

    private String complaint;
    private String description;

    public BusinessReschedule(){}

    public BusinessReschedule(String complaint, String description) {
        this.complaint = complaint;
        this.description = description;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
