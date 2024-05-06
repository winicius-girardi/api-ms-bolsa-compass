package com.compassuol.sp.challenge.msaddress.dto;

public class AddressUserRequestDto {

    private  Long id_user;
    private  String cep;

    public AddressUserRequestDto(Long id_user, String cep) {
        this.id_user = id_user;
        this.cep = cep;
    }

    public AddressUserRequestDto() {
    }
    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
