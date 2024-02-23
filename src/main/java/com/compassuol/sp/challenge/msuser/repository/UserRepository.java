package com.compassuol.sp.challenge.msuser.repository;

import com.compassuol.sp.challenge.msuser.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {


    Person findByCpf(String cpf);
    Person findByEmail(String email);
}