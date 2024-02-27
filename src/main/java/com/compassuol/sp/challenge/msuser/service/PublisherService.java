package com.compassuol.sp.challenge.msuser.service;

import com.compassuol.sp.challenge.msuser.dto.publisherMqueueDto.UserAddressPublisher;
import com.compassuol.sp.challenge.msuser.dto.publisherMqueueDto.UserNotificationPublisher;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.PublisherRequestException;
import com.compassuol.sp.challenge.msuser.mqueue.AddressPublisher;
import com.compassuol.sp.challenge.msuser.mqueue.NotificationPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PublisherService {

    @Autowired
    private AddressPublisher addressPublisher;

    @Autowired
    private NotificationPublisher notificationPublisher;



    @Transactional
    public void sendNotification(LocalDateTime date, String email, String create) {
        UserNotificationPublisher notification = new UserNotificationPublisher(email,create,date.toString());
        try {
            notificationPublisher.sendNotification(notification);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PublisherRequestException("Error to send notification"+ e.getMessage());
        }
    }
    @Transactional
    public void sendAddress(String cep, Long id) {
        UserAddressPublisher address = new UserAddressPublisher(id,cep);
        try {
            addressPublisher.requestAddress(address);
        } catch (Exception e) {
            throw new PublisherRequestException("Error to send address");
        }
    }
}
