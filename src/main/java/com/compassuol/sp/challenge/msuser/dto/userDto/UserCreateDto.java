package com.compassuol.sp.challenge.msuser.dto.userDto;

public class UserCreateDto {

    private String firstName;
    private String lastName;

    private String cpf;

    private String birthdate;
    private String email;

    private String cep;

    private String password;

    private boolean active;


    public UserCreateDto() {
    }
    public UserCreateDto(String firstName, String lastName, String cpf, String birthdate, String email, String cep, String password, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.email = email;
        this.cep = cep;
        this.password = password;
        this.active = active;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
