package com.compassuol.sp.challenge.msuser.dto.userDto;

public class PasswordChangeDto {

    private String password;

    public PasswordChangeDto(String password) {
        this.password = password;
    }
    public PasswordChangeDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
