package com.compassuol.sp.challenge.msuser;


import com.compassuol.sp.challenge.msuser.exception.customexceptions.UserValidationException;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.service.ValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.compassuol.sp.challenge.msuser.common.UserConstraints.USER_CREATE_DTO_INVALID;
import static com.compassuol.sp.challenge.msuser.common.UserConstraints.USER_CREATE_DTO_VALID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.compassuol.sp.challenge.msuser.common.ValidatorConstraints.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidatorServiceTests {

    @InjectMocks
    private ValidatorService validatorService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void validateBirthdateTest() {
        validatorService.validateBirthdate(VALID_BIRTHDATE);
    }
    @Test
    public void invalidBirthdateTest() {
        assertThrows(UserValidationException.class, () -> validatorService.validateBirthdate(INVALID_BIRTHDATE));
    }

    @Test
    public void validateCepTest() {
        validatorService.validateCep(VALID_CEP);
    }
    @Test
    public void invalidCepTest() {
        assertThrows(UserValidationException.class, () -> validatorService.validateCep(INVALID_CEP));
    }

    @Test
    public void validateNameTest() {
        validatorService.validateName(VALID_NAME, VALID_LAST_NAME);
    }
    @Test
    public void invalidNameTest() {
        assertThrows(UserValidationException.class, () -> validatorService.validateName(INVALID_NAME, VALID_LAST_NAME));
    }
    @Test
    public void invalidLastNameTest() {
        assertThrows(UserValidationException.class, () -> validatorService.validateName(VALID_NAME, INVALID_LAST_NAME));
    }
    @Test
    public void invalidEmailTest() {
        assertThrows(UserValidationException.class, () -> validatorService.validateEmail(INVALID_EMAIL));
    }
    @Test
    public void validateEmailTest() {
        when(userRepository.findByEmail(VALID_EMAIL)).thenReturn(null);
        validatorService.validateEmail(VALID_EMAIL);
    }
    @Test
    public void invalidPasswordTest() {
        assertThrows(UserValidationException.class, () -> validatorService.validatePassword(INVALID_PASSWORD));
    }
    @Test
    public void validatePasswordTest() {
        validatorService.validatePassword(VALID_PASSWORD);
    }
    @Test
    public void validatePersonTest() {
        when(userRepository.getUserByCpf(USER_CREATE_DTO_VALID.getCpf())).thenReturn(null);
        when(userRepository.findByEmail(USER_CREATE_DTO_VALID.getEmail())).thenReturn(null);
        validatorService.validatePerson(USER_CREATE_DTO_VALID);
    }

    @Test
    public void invalidPersonTest() {
        assertThrows(UserValidationException.class, () -> validatorService.validatePerson(USER_CREATE_DTO_INVALID));
    }
    @Test
    public void validateLoginTest() {
        validatorService.validateLogin(LOGIN_REQUEST_DTO_VALID);
    }
    @Test
    public void invalidLoginTest() {
        assertThrows(UserValidationException.class, () -> validatorService.validateLogin(LOGIN_REQUEST_DTO_INVALID));
    }
    @Test
    public void validateEmailSintaxTest() {
        validatorService.validateEmailSintax(VALID_EMAIL);
    }
    @Test
    public void invalidEmailSintaxTest() {
        assertThrows(UserValidationException.class, () -> validatorService.validateEmailSintax(INVALID_EMAIL));
    }
    @Test
    public void validateCpfTest() {
        when(userRepository.getUserByCpf(VALID_CPF)).thenReturn(null);
        validatorService.validateCpf(VALID_CPF);
    }

    @Test
    public void invalidCpfTest() {
        when(userRepository.getUserByCpf(INVALID_CPF)).thenReturn(null);
        assertThrows(UserValidationException.class, () -> validatorService.validateCpf(INVALID_CPF));
    }


}
