package com.compassuol.sp.challenge.msnotification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

//    Processing message: {"email":"teste@teste.com","event":"CREATE","date":"2024-02-27T23:23:16.418543200"}
//    Processing message: {"email":"teste@teste.com","event":"CREATE","date":"2024-02-27T23:23:35.191678300"}
//    Processing message: {"email":"teste@teste.com","event":"LOGIN","date":"2024-02-28T02:10:35.135848300"}
//    Processing message: {"email":"teste1@teste.com","event":"CREATE","date":"2024-02-28T02:10:46.959438800"}
    public void processRequest(String message) {
        System.out.println("Processing message: " + message);
    }
}
