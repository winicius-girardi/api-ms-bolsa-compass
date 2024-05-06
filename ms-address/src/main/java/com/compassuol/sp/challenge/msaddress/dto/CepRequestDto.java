package com.compassuol.sp.challenge.msaddress.dto;

public class CepRequestDto {

    private String cep;

    public CepRequestDto(String cep) {
        this.cep = cep;
    }

    public CepRequestDto() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
