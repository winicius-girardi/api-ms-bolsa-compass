package com.compassuol.sp.challenge.msuser.mqueue;

import com.compassuol.sp.challenge.msuser.dto.publisherMqueueDto.UserNotificationPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationPublisher {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queueEmissorNotification;



    public void sendNotification(UserNotificationPublisher userNotificationPublisher) throws JsonProcessingException {
        var json = convertIntoJson(userNotificationPublisher);
        rabbitTemplate.convertAndSend(queueEmissorNotification.getName(), json);
    }



    private String convertIntoJson(UserNotificationPublisher userNotificationPublisher ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userNotificationPublisher);
    }

}
