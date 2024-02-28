package com.compassuol.sp.challenge.msuser.service;

import com.compassuol.sp.challenge.msuser.dto.userDto.LoginRequestDto;
import com.compassuol.sp.challenge.msuser.dto.userDto.UserCreateDto;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.UserValidationException;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//TODO: MAKE CUSTOM EXCEPTIONS
@Service
public class ValidatorService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    //TODO: VALIDAR SE CAMPO ACTIVE É BOOLEAN
    public void validatePerson(UserCreateDto user) {
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateCpf(user.getCpf());
        validateName(user.getFirstName(),user.getLastName());
        validateCep(user.getCep());
        validateBirthdate(user.getBirthdate());

    }

    private void validateBirthdate(String birthDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate.parse(birthDate, formatter);
        } catch (DateTimeParseException e) {
            throw new UserValidationException("Invalid birthdate");
        }
    }

    private void validateCep(String cep) {
        if(!cep.matches("\\d{5}-\\d{3}$")){
            throw new UserValidationException("CEP must have the following format: 12345-123");
        }
    }

    public void validateName(String firstName, String lastName) {
        if(firstName.replaceAll(" ","").length()<3){
            throw new UserValidationException("First name must have at least 3 characters");
        }
        if(lastName.length()<3){
            throw new UserValidationException("Last name must have at least 3 characters");
        }
    }

    public void validateLogin(LoginRequestDto loginRequestDto) {
        validateEmailSintax(loginRequestDto.getEmail());
        validatePassword(loginRequestDto.getPassword());
        var loginAttempt= userRepository.findByEmail(loginRequestDto.getEmail());
        if(loginAttempt==null){
            throw new UserValidationException("Invalid username or password");
        }
        if(!passwordEncoder.matches(loginRequestDto.getPassword(),loginAttempt.getPassword())){
            throw new UserValidationException("Invalid username or password");
        }


    }




    public void validateCpf(String cpf) {
        cpf=cpf.replaceAll("[.-]","");
        if(cpf.length()!=11){
            throw new UserValidationException("CPF must have 11 characters");
        }
        if(!cpf.matches("\\d+")){
            throw new UserValidationException("CPF must have only numbers");
        }
        if(userRepository.findByCpf(cpf)!=null){
            throw new UserValidationException("CPF already registered");
        }
        int[] multiplicadores1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadores2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * multiplicadores1[i];
        }

        int resto = soma % 11;
        int digitoVerificador1 = resto < 2 ? 0 : 11 - resto;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * multiplicadores2[i];
        }

        resto = soma % 11;
        int digitoVerificador2 = resto < 2 ? 0 : 11 - resto;

        if(Integer.parseInt(String.valueOf(cpf.charAt(9))) == digitoVerificador1 &&
                Integer.parseInt(String.valueOf(cpf.charAt(10))) == digitoVerificador2){

        }
        else {
            throw new UserValidationException("Invalid CPF");
        }
    }

    public void validateChangePassword(String newPassword, String oldPassword) {
        validatePassword(newPassword);
        if(passwordEncoder.matches(newPassword,oldPassword)){
            throw new UserValidationException("New password must be different from the old one");
        }

    }

    public void validatePassword(String newPassword) {
        if(newPassword.replaceAll(" ","").length()<6){
            throw new UserValidationException("Password must have at least 6 characters");
        }
    }
    public void  validateEmail(String email){
        validateEmailSintax(email);
        if(userRepository.findByEmail(email)!=null){
            throw new UserValidationException("Email already registered");
        }


    }

    private  void validateEmailSintax(String email) {
        if(!email.matches("^(.+)@(.+)$")){
            throw new UserValidationException("Invalid email");
        }
    }
}
