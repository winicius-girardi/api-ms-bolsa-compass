package com.compassuol.sp.challenge.msaddress.dto;

import com.compassuol.sp.challenge.msaddress.entity.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {



    public Address dtoToEntity(AddressResponseDto addressResponseDto) {
        return new Address(addressResponseDto.getCep(),
                addressResponseDto.getLogradouro(),
                addressResponseDto.getComplemento(),
                addressResponseDto.getBairro(),
                addressResponseDto.getLocalidade(),
                addressResponseDto.getUf(),
                addressResponseDto.getIbge(),
                addressResponseDto.getGia(),
                addressResponseDto.getDdd(),
                addressResponseDto.getSiafi());
    }

    public AddressResponseDto entityToDto(Address address) {
        return new AddressResponseDto(address.getCep(),
                address.getLogradouro(),
                address.getComplemento(),
                address.getBairro(),
                address.getLocalidade(),
                address.getUf(),
                address.getIbge(),
                address.getGia(),
                address.getDdd(),
                address.getSiafi());
    }
}
