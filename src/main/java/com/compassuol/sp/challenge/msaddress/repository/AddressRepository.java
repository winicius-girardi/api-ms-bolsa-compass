package com.compassuol.sp.challenge.msaddress.repository;

import com.compassuol.sp.challenge.msaddress.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCep(String cep);
}