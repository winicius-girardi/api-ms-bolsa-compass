package com.compassuol.sp.challenge.msuser.mqueue;

import com.compassuol.sp.challenge.msuser.dto.publisherMqueueDto.UserAddressPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressPublisher {

    @Autowired
    private  RabbitTemplate rabbitTemplate;




    @Autowired
    private  Queue queueEmissorAddress;


    public void requestAddress(UserAddressPublisher userAddressPublisher) throws JsonProcessingException {
        var json = convertIntoJson(userAddressPublisher);
        rabbitTemplate.convertAndSend(queueEmissorAddress.getName(), json);
    }


    private String convertIntoJson(UserAddressPublisher userAddressPublisher) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userAddressPublisher);
    }

}
