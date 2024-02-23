package com.compassuol.sp.challenge.msuser.service;

import com.compassuol.sp.challenge.msuser.dto.LoginRequestDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserCreateDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserResponseDto;
import com.compassuol.sp.challenge.msuser.dto.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//TODO : MAKE CUSTOM EXCEPTIONS
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    private UserValidatorService userValidatorService;
    @Transactional
    public UserResponseDto createPerson(UserCreateDto personRequestDto) {
        var person = userMapper.createDtoToEntity(personRequestDto);
        userValidatorService.validatePerson(person);
        return userMapper.entityToResponse(userRepository.save(person));
    }
    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        return userMapper.entityToResponse(userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found")));
    }


    @Transactional
    public void login(LoginRequestDto loginRequestDto) {
        userValidatorService.validateLogin(loginRequestDto);
    }
    @Transactional
    public void changePassword(Long id, String newPassword) {
        var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        userValidatorService.validatePassword(newPassword);
        user.setPassword(newPassword);
        userRepository.save(user);
    }
    @Transactional
    public void changeUserState(Long id, boolean active) {
        var foundUser=userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User not found"));
        foundUser.setActive(active);
    }
}
