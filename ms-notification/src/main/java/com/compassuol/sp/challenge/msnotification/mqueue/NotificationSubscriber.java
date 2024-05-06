package com.compassuol.sp.challenge.msnotification.mqueue;


import com.compassuol.sp.challenge.msnotification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotificationSubscriber {



    @Autowired
    private NotificationService notificationService;


    @RabbitListener(queues = "${mq.queue.ms-notifications}")
    public void receiveMessage(@Payload String message){
        notificationService.processRequest(message);
    }

}
