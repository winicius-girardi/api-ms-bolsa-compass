package com.compassuol.sp.challenge.msuser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {



    @Value("${mq.queues.ms-address}")
    private String  queueEmissorAddress ;

    @Value("${mq.queues.ms-notifications}")
    private String queueEmissorNotification;

    @Bean
    public Queue queueEmissorAddress() {
        return new Queue(queueEmissorAddress,true);
    }

    @Bean
    public Queue queueEmissorNotification() {
        return new Queue(queueEmissorNotification,true);
    }
}
