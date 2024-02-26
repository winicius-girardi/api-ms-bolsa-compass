package com.compassuol.sp.challenge.msaddress.feign;


import com.compassuol.sp.challenge.msaddress.dto.AddressResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address",url="viacep.com.br/ws")
public interface AddressFeign {


     @GetMapping("/{cep}/json")
     ResponseEntity<AddressResponseDto> getAddressByCep(@PathVariable String cep);

}
