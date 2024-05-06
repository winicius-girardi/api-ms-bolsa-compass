package com.compassuol.sp.challenge.msnotification.dto;

public class NotificationRequestDto {
    private String email;
    private String event;
    private String date;

    public NotificationRequestDto(String email, String event, String date) {
        this.email = email;
        this.event = event;
        this.date = date;
    }
    public NotificationRequestDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
