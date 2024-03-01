package com.compassuol.sp.challenge.msuser.service;

import com.compassuol.sp.challenge.msuser.dto.publisherMqueueDto.UserAddressPublisher;
import com.compassuol.sp.challenge.msuser.dto.publisherMqueueDto.UserNotificationPublisher;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.PublisherRequestException;
import com.compassuol.sp.challenge.msuser.mqueue.AddressPublisher;
import com.compassuol.sp.challenge.msuser.mqueue.NotificationPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Service
public class PublisherService {

    @Autowired
    private AddressPublisher addressPublisher;

    @Autowired
    private NotificationPublisher notificationPublisher;



    @Transactional
    public void sendNotification(String email, String create) {
        //TODO - localDateTime SHOULD BE IN ISO FORMAT

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        String formattedDateTime = localDateTime.format(formatter);

        String dateTimeWithZ = formattedDateTime + "Z";

        UserNotificationPublisher notification = new UserNotificationPublisher(email,create,dateTimeWithZ);
        try {
            notificationPublisher.sendNotification(notification);
        } catch (Exception e) {
            throw new PublisherRequestException("Error to send notification");
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
