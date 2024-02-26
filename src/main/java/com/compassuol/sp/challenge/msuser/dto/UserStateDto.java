package com.compassuol.sp.challenge.msuser.dto;

import jdk.jfr.BooleanFlag;

public class UserStateDto {


    private Boolean active;

    public UserStateDto() {
    }

    public UserStateDto(Boolean active) {

        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
