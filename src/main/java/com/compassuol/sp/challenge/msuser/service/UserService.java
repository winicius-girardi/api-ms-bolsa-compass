package com.compassuol.sp.challenge.msuser.service;

import com.compassuol.sp.challenge.msuser.dto.LoginRequestDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserCreateDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserResponseDto;
import com.compassuol.sp.challenge.msuser.dto.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.UserValidationException;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//TODO : MAKE CUSTOM EXCEPTIONS
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidatorService validatorService;
    @Transactional
    public UserResponseDto createUser(UserCreateDto userRequestDto) {
        validatorService.validatePerson(userRequestDto);
        var user = userMapper.createDtoToEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userRepository.save(user);
        return userMapper.entityToResponse(savedUser);
    }
    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        return userMapper.entityToResponse(userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found")));
    }


    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto) {
        validatorService.validateLogin(loginRequestDto);
    }
    @Transactional
    public void changePassword(Long id, String newPassword) {
        var user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found"));
        validatorService.validateChangePassword(newPassword,user.getPassword());
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    @Transactional
    public void changeUserState(Long id, boolean active) {
        var foundUser=userRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("User not found"));
        foundUser.setActive(active);
    }
}
