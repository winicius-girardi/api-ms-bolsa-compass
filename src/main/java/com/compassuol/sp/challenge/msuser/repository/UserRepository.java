package com.compassuol.sp.challenge.msuser.repository;

import com.compassuol.sp.challenge.msuser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByCpf(String cpf);
    User findByEmail(String email);
}