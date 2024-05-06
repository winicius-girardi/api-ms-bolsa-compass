package com.compassuol.sp.challenge.msuser.dto.publisherMqueueDto;

public class UserAddressPublisher {

    private Long id_user;
    private String cep;

    public UserAddressPublisher(Long id_user, String cep) {
        this.id_user = id_user;
        this.cep = cep;
    }

    public UserAddressPublisher() {
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
