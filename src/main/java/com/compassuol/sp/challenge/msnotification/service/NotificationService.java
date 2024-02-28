package com.compassuol.sp.challenge.msnotification.service;

import com.compassuol.sp.challenge.msnotification.dto.NotificationRequestDto;
import com.compassuol.sp.challenge.msnotification.entity.Notification;
import com.compassuol.sp.challenge.msnotification.repository.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compassuol.sp.challenge.msnotification.entity.Notification.Event;

import java.time.LocalDateTime;

import static com.compassuol.sp.challenge.msnotification.entity.Notification.Event.*;

@Service
public class NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;
    public void processRequest(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        NotificationRequestDto notification;
        try {
            notification = objectMapper.readValue(message, NotificationRequestDto.class);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao converter json");
            return;
        }
        if(!validation(notification)){
            return;
        }
        saveNotification(notification);
    }

    private boolean validation(NotificationRequestDto notification) {
        if (!notification.getEmail().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Email inválido");
            return false;
        }
        if (notification.getDate().matches("^(\\d{4}-\\d{2}-\\d{2})T(\\d{2}:\\d{2}:\\d{2})Z$")) {
            System.out.println("Data inválida");
            return false;
        }
        if(notification.getEvent().matches("LOGIN||CREATE||UPDATE||UPDATE_PASSWORD")){
            System.out.println("Evento inválido");
            return false;
        }
        return true;
    }

    public void saveNotification(NotificationRequestDto notificationRequestDto) {
        Event event = Event.valueOf(notificationRequestDto.getEvent());
        Notification notification = new Notification(notificationRequestDto.getEmail(), event , LocalDateTime.parse(notificationRequestDto.getDate()));
        notificationRepository.save(notification);
    }
}
