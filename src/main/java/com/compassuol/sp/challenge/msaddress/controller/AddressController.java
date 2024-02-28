package com.compassuol.sp.challenge.msaddress.controller;

import com.compassuol.sp.challenge.msaddress.dto.AddressResponseDto;
import com.compassuol.sp.challenge.msaddress.dto.CepRequestDto;
import com.compassuol.sp.challenge.msaddress.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//TODO REMOVE THIS CONTROLLER IF NOT HOST ON AMAZON
@RestController
public class AddressController {


    @Autowired
    private  AddressService addressService;

    @GetMapping("/address")
    public ResponseEntity<AddressResponseDto> getAddressByCep(@RequestBody CepRequestDto cep) {
        var address = addressService.getAddressByCep(cep.getCep());
        return ResponseEntity.ok(address);

    }
}
