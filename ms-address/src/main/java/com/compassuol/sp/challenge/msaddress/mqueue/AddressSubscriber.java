package com.compassuol.sp.challenge.msaddress.mqueue;

import com.compassuol.sp.challenge.msaddress.dto.AddressMapper;
import com.compassuol.sp.challenge.msaddress.dto.AddressResponseDto;
import com.compassuol.sp.challenge.msaddress.dto.AddressUserRequestDto;
import com.compassuol.sp.challenge.msaddress.entity.Address;
import com.compassuol.sp.challenge.msaddress.service.AddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AddressSubscriber {





    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressService addressService;

    @RabbitListener(queues = "${mq.queue.ms-address}")
    public void receiveMessage(@Payload String message){//{"id_user":34,"cep":"69999-999"}
        addressService.processRequest(message);
    }





}
