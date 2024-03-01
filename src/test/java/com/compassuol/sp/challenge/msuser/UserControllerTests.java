package com.compassuol.sp.challenge.msuser;
import com.compassuol.sp.challenge.msuser.controller.UserController;
import com.compassuol.sp.challenge.msuser.dto.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.UserValidationException;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.service.PublisherService;
import com.compassuol.sp.challenge.msuser.service.UserService;
import com.compassuol.sp.challenge.msuser.service.ValidatorService;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.compassuol.sp.challenge.msuser.common.UserConstraints.*;
import static com.compassuol.sp.challenge.msuser.common.UserConstraints.USER_VALID;
import static com.compassuol.sp.challenge.msuser.common.ValidatorConstraints.INVALID_ID;
import static com.compassuol.sp.challenge.msuser.common.ValidatorConstraints.VALID_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Test
    public void createUserTest_WithInvalidUser() {
        when(userService.createUser(USER_CREATE_DTO_INVALID)).thenThrow(UserValidationException.class);
        assertThrows(UserValidationException.class,()->userController.createUser(USER_CREATE_DTO_INVALID));

    }

    @Test
    public void createUserTest_WithValidUser() {
        when(userService.createUser(USER_CREATE_DTO_VALID)).thenReturn(USER_RESPONSE_DTO_VALID);

        var sut=userController.createUser(USER_CREATE_DTO_VALID);
        assertThat(sut.getStatusCode().toString()).isEqualTo("201 CREATED");
    }
    @Test
    public void findUserByIdTest_WithValidUserId() {
        when(userService.findUserById(VALID_ID)).thenReturn(USER_RESPONSE_DTO_VALID);

        var sut=userController.findUserById(VALID_ID);
        assertThat(sut.getStatusCode().toString()).isEqualTo("200 OK");
        assertThat(sut.getBody().getId()).isEqualTo(1L);
    }
    @Test
    public void findUserByIdTest_WithInvalidUserId() {
        when (userService.findUserById(INVALID_ID)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class,()->userController.findUserById(INVALID_ID));
    }

    @Test
    public void changeUserPasswordTest_WithValidUserId() {
        assertThat(userController.changePassword(VALID_ID,VALID_PASSWORD_CHANGE_DTO).getStatusCode().toString()).isEqualTo("204 NO_CONTENT");
    }

    @Test
    public void changeUserPasswordTest_WithInvalidUserId() {
        when(userController.changePassword(INVALID_ID,VALID_PASSWORD_CHANGE_DTO)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> userController.changePassword(INVALID_ID,VALID_PASSWORD_CHANGE_DTO));
    }


}
