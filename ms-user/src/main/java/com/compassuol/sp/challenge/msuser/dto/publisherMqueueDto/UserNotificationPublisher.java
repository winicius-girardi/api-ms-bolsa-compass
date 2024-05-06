package com.compassuol.sp.challenge.msuser.dto.publisherMqueueDto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class UserNotificationPublisher {

    private String email;

    private String event;

    private String date;

    public UserNotificationPublisher(String email, String event, String date) {
        this.email = email;
        this.event = event;
        this.date = date;
    }
    public UserNotificationPublisher() {
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
