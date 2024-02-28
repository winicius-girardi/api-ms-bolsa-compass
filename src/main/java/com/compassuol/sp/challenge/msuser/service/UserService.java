package com.compassuol.sp.challenge.msuser.service;

import com.compassuol.sp.challenge.msuser.dto.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.dto.userDto.PasswordChangeDto;
import com.compassuol.sp.challenge.msuser.dto.userDto.UserCreateDto;
import com.compassuol.sp.challenge.msuser.dto.userDto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.entity.User;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private ValidatorService validatorService;
    @Transactional
    public UserResponseDto createUser(UserCreateDto userRequestDto) {
        validatorService.validatePerson(userRequestDto);
        var user = userMapper.createDtoToEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userRepository.save(user);

        publisherService.sendNotification(savedUser.getEmail(),"CREATE");
        publisherService.sendAddress(savedUser.getCep(),savedUser.getId());
        return userMapper.entityToResponse(savedUser);
    }
    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        return userMapper.entityToResponse(userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found")));
    }


    @Transactional
    public void changePassword(Long id, PasswordChangeDto newPassword) {
        String password = newPassword.getPassword();
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found"));
        validatorService.validateChangePassword(password,user.getPassword());
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        publisherService.sendNotification(user.getEmail(),"UPDATE_PASSWORD");
    }
    @Transactional
    public void changeUserState(Long id, boolean active) {
        var foundUser=userRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("User not found"));
        foundUser.setActive(active);
        publisherService.sendNotification(foundUser.getEmail(),"UPDATE");
    }
}
