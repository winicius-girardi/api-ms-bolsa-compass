package com.compassuol.sp.challenge.msuser.dto.user;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;

    private String cpf;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime birthdate;
    private String email;

    private String cep;

    private String password;

    public UserResponseDto(Long id, String firstName, String lastName, String cpf, LocalDateTime birthdate, String email, String cep, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.email = email;
        this.cep = cep;
        this.password = password;
    }
    public UserResponseDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
