package com.compassuol.sp.challenge.msaddress.service;

import com.compassuol.sp.challenge.msaddress.dto.AddressMapper;
import com.compassuol.sp.challenge.msaddress.dto.AddressResponseDto;
import com.compassuol.sp.challenge.msaddress.dto.AddressUserRequestDto;
import com.compassuol.sp.challenge.msaddress.entity.Address;
import com.compassuol.sp.challenge.msaddress.feign.AddressFeign;
import com.compassuol.sp.challenge.msaddress.repository.AddressRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private  AddressFeign addressFeign;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;


    public AddressResponseDto getAddressByCep(String cep) {

        if(addressRepository.findByCep(cep)!=null){
            System.out.println("CEP JÁ  EXISTE NO BANCO DE DADOS");
            return null;
        }
        AddressResponseDto addressRequest= addressFeign.getAddressByCep(cep);
        if(addressRequest.getCep()==null&&addressRequest.getBairro()==null){
            System.out.println("CEP NÃO EXISTE");
            return null;
        }
        return addressRequest;
    }

    @Transactional
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    @Transactional
    public void processRequest(String message){

        ObjectMapper objectMapper = new ObjectMapper();
        AddressUserRequestDto addressUserRequestDto;
        String cep;

        try {
            addressUserRequestDto = objectMapper.readValue(message, AddressUserRequestDto.class);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao converter json");
            return;
        }
        cep=processCep(addressUserRequestDto.getCep());
        if(!checkCep(cep)){
            System.out.println("CEP INVÁLIDO");
            return;
        }
        addressUserRequestDto.setCep(cep);
        AddressResponseDto addressRequest=getAddressByCep(cep);

        if(addressRequest==null){
            return;
        }

        Address address=addressMapper.dtoToEntity(addressRequest);
        address.setCep(cep);
        address.setId_user(addressUserRequestDto.getId_user());

        saveAddress(address);
    }
    public String processCep(String cep){
        return cep.replaceAll("-","");
    }
    public boolean checkCep(String cep){
        return cep.matches("[0-9]{8}");
    }
}
