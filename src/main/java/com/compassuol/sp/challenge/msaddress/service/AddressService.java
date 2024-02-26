package com.compassuol.sp.challenge.msaddress.service;

import com.compassuol.sp.challenge.msaddress.dto.AddressMapper;
import com.compassuol.sp.challenge.msaddress.dto.AddressResponseDto;
import com.compassuol.sp.challenge.msaddress.feign.AddressFeign;
import com.compassuol.sp.challenge.msaddress.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private  AddressFeign addressFeign;

    private AddressMapper addressMapper;

    @Autowired
    private AddressRepository addressRepository;
    public AddressResponseDto getAddressByCep(String cep) {
        var addressRequest= addressFeign.getAddressByCep(cep);
        return addressRequest.getBody();
    }

    @Transactional
    public void saveAddress(AddressResponseDto address) {
        //TODO SETAR O ID DO USER COM O CHAVE ESTRANGEIRA NO ADDRESS
        addressRepository.save(addressMapper.dtoToEntity(address));
    }
}
