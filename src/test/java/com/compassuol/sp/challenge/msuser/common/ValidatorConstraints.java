package com.compassuol.sp.challenge.msuser.common;

import com.compassuol.sp.challenge.msuser.dto.userDto.LoginRequestDto;

public class ValidatorConstraints {

    public static final String VALID_BIRTHDATE = "16/01/2001";
    public static final String INVALID_BIRTHDATE = "";
    public static final String VALID_CEP = "01001-000";
    public static final String INVALID_CEP = "01001000";
    public static final String VALID_NAME = "John";
    public static final String INVALID_NAME = "J";
    public static final String VALID_LAST_NAME = "Doe";
    public static final String INVALID_LAST_NAME = "D";
    public static final String VALID_EMAIL = "teste@teste.com";
    public static final String INVALID_EMAIL = "teste.com";
    public static final String VALID_PASSWORD = "testeteste";
    public static final String INVALID_PASSWORD = "";

    public static final LoginRequestDto LOGIN_REQUEST_DTO_VALID = new LoginRequestDto("teste@teste.com","testeteste");
    public static final LoginRequestDto LOGIN_REQUEST_DTO_INVALID = new LoginRequestDto("","");

    public static final String VALID_CPF = "209.385.740-51";
    public static final String INVALID_CPF = "209.385.740-52";


}
