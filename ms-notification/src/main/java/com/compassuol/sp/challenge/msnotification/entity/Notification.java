package com.compassuol.sp.challenge.msnotification.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Document
public class Notification {


    private String email;

    private Event event;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime date;

    public Notification(String email, Event event, LocalDateTime date) {
        this.email = email;
        this.event = event;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(email, that.email) && event == that.event && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, event, date);
    }

    public enum Event {
        LOGIN, CREATE, UPDATE, UPDATE_PASSWORD
    }
}
