package com.compassuol.sp.challenge.msuser.repository;

import com.compassuol.sp.challenge.msuser.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}