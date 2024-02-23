package com.compassuol.sp.challenge.msuser.dto.mapper;

import com.compassuol.sp.challenge.msuser.dto.user.UserCreateDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserResponseDto;
import com.compassuol.sp.challenge.msuser.entity.User;

import java.time.LocalDateTime;


public class UserMapper {





    public User createDtoToEntity(UserCreateDto userCreateDto) {
        return new User(userCreateDto.getFirstName(),
                userCreateDto.getLastName(),
                userCreateDto.getCpf(),
                LocalDateTime.parse(userCreateDto.getBirthdate().toString()),
                userCreateDto.getEmail(),
                userCreateDto.getCep(),
                userCreateDto.isActive(),
                userCreateDto.getPassword());
    }
    public UserResponseDto entityToResponse(User user) {
        return new UserResponseDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCpf(),
                user.getBirthDate(),
                user.getEmail(),
                user.getCep(),
                user.getPassword());
    }

}
