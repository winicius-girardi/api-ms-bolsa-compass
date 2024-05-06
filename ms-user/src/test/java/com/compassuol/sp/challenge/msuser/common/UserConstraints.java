package com.compassuol.sp.challenge.msuser.common;

import com.compassuol.sp.challenge.msuser.dto.userDto.PasswordChangeDto;
import com.compassuol.sp.challenge.msuser.dto.userDto.UserCreateDto;
import com.compassuol.sp.challenge.msuser.dto.userDto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.dto.userDto.UserStateDto;
import com.compassuol.sp.challenge.msuser.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserConstraints {



   public static final UserCreateDto USER_CREATE_DTO_VALID = new UserCreateDto("John", "Doe", "209.385.740-51", "16/01/2001","teste@teste.com","01001-000","testeteste",true);

   public static final UserCreateDto USER_CREATE_DTO_INVALID = new UserCreateDto("a","a","a","a","a","a","a",true);

   public static final User USER_VALID = new User("John", "Doe", "teste@teste.com", LocalDateTime.of(2001, 1, 16, 0, 0),"209.385.740-51","01001-000",true,"teste");

   public static final User USER_INVALID = new User("","","",LocalDateTime.of(2001, 1, 16, 0, 0),"","",true,"");

   public static final UserResponseDto USER_RESPONSE_DTO_VALID = new UserResponseDto(1L,"John", "Doe","209.385.740-51","16/01/2001","teste@teste.com","01001-000",true );

   public static final PasswordChangeDto VALID_PASSWORD_CHANGE_DTO = new PasswordChangeDto("testeteste");

   public static final PasswordChangeDto INVALID_PASSWORD_CHANGE_DTO = new PasswordChangeDto("");

   public static final UserStateDto USER_STATE_DTO_VALID = new UserStateDto(true);

}
