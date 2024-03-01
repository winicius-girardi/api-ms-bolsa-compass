package com.compassuol.sp.challenge.msuser;
import com.compassuol.sp.challenge.msuser.controller.UserController;
import com.compassuol.sp.challenge.msuser.dto.mapper.UserMapper;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.compassuol.sp.challenge.msuser.common.UserConstraints.*;
import static com.compassuol.sp.challenge.msuser.common.UserConstraints.USER_VALID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static com.compassuol.sp.challenge.msuser.common.ValidatorConstraints.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PublisherService publisherService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void createUserTest_WithInvalidUser() {
        assertThrows(UserValidationException.class,()->userController.createPerson(USER_CREATE_DTO_INVALID));
    }

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
    }
}
