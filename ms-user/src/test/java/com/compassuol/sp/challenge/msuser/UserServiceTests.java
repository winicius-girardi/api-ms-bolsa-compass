package com.compassuol.sp.challenge.msuser;

import com.compassuol.sp.challenge.msuser.dto.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.UserValidationException;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.service.PublisherService;
import com.compassuol.sp.challenge.msuser.service.UserService;
import com.compassuol.sp.challenge.msuser.service.ValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.compassuol.sp.challenge.msuser.common.UserConstraints.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.compassuol.sp.challenge.msuser.common.ValidatorConstraints.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    PublisherService publisherService;

    @Mock
    private UserMapper userMapper;

    @Mock
    PasswordEncoder passwordEncoder;



    //mvn clean test jacoco:report
    @Test
    public void createUserTest_WithValidUser() {
        when(userRepository.save(USER_VALID)).thenReturn(USER_VALID);
        when(userMapper.entityToResponse(USER_VALID)).thenReturn(USER_RESPONSE_DTO_VALID);
        when(userMapper.createDtoToEntity(USER_CREATE_DTO_VALID)).thenReturn(USER_VALID);
        when(passwordEncoder.encode(USER_VALID.getPassword())).thenReturn(USER_VALID.getPassword());

        var sut=userService.createUser(USER_CREATE_DTO_VALID);
        assertThat(sut.isActive()).isEqualTo(USER_CREATE_DTO_VALID.isActive());
        assertThat(sut.getBirthdate()).isEqualTo(USER_CREATE_DTO_VALID.getBirthdate());
        assertThat(sut.getCep()).isEqualTo(USER_CREATE_DTO_VALID.getCep());
        assertThat(sut.getCpf()).isEqualTo(USER_CREATE_DTO_VALID.getCpf());
        assertThat(sut.getEmail()).isEqualTo(USER_CREATE_DTO_VALID.getEmail());
        assertThat(sut.getFirstName()).isEqualTo(USER_CREATE_DTO_VALID.getFirstName());
        assertThat(sut.getLastName()).isEqualTo(USER_CREATE_DTO_VALID.getLastName());
        assertThat(sut.getId()).isEqualTo(1L);
    }

//    @Test
//    public void invalidCreateUserTest_WithInvalidUser() {
//        when(validatorService.validatePerson(USER_CREATE_DTO_INVALID)).thenThrow(UserValidationException.class);
//        assertThrows(UserValidationException.class, () -> userService.createUser(USER_CREATE_DTO_INVALID));
//
//    }
    @Test
    public void findUserByIdTest_ReturnUser() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(USER_VALID));
        when(userMapper.entityToResponse(USER_VALID)).thenReturn(USER_RESPONSE_DTO_VALID);
        var sut=userService.findUserById(1L);
        assertThat(sut.getId()).isEqualTo(1L);
        assertThat(sut.isActive()).isEqualTo(USER_VALID.isActive());
        assertThat(sut.getBirthdate()).isEqualTo(USER_RESPONSE_DTO_VALID.getBirthdate());
        assertThat(sut.getCep()).isEqualTo(USER_RESPONSE_DTO_VALID.getCep());
        assertThat(sut.getCpf()).isEqualTo(USER_RESPONSE_DTO_VALID.getCpf());
        assertThat(sut.getEmail()).isEqualTo(USER_RESPONSE_DTO_VALID.getEmail());
        assertThat(sut.getFirstName()).isEqualTo(USER_RESPONSE_DTO_VALID.getFirstName());
        assertThat(sut.getLastName()).isEqualTo(USER_RESPONSE_DTO_VALID.getLastName());
    }
    @Test
    public void findUserByIdTest_ReturnUserNotFound() {
        when(userRepository.findById(1L)).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> userService.findUserById(1L));
    }

    @Test
    public void changePasswordValid_UserExists() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(USER_VALID));
        when(passwordEncoder.encode(VALID_PASSWORD)).thenReturn(VALID_PASSWORD);
        userService.changePassword(1L,VALID_PASSWORD_CHANGE_DTO);
    }
    //@Test
//    public void changePasswordInvalid_UserExists() {
//        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(USER_VALID));
//        //when(validatorService.validateChangePassword(INVALID_PASSWORD, USER_INVALID.getPassword())).thenThrow(UserValidationException.class);
//        assertThrows(UserValidationException.class, () -> userService.changePassword(1L,INVALID_PASSWORD_CHANGE_DTO));
//    }

    @Test
    public void changeUserStateTest() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(USER_VALID));
        userService.changeUserState(1L,true);
        assertThat(USER_VALID.isActive()).isEqualTo(true);
    }
    @Test
    public void changeUserStateTest_UserNotFound() {
        when(userRepository.findById(1L)).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> userService.changeUserState(1L,false));
    }
}
