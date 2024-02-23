package com.compassuol.sp.challenge.msuser.service;

import com.compassuol.sp.challenge.msuser.dto.LoginRequestDto;
import com.compassuol.sp.challenge.msuser.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import org.springframework.stereotype.Service;

//TODO: MAKE CUSTOM EXCEPTIONS
@Service
public class UserValidatorService {

    private UserRepository userRepository;
    public void validatePerson(User user) {
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        if(user.getFirstName().replaceAll(" ","").length()<3){
            throw new IllegalArgumentException("First name must have at least 3 characters");
        }
        if(user.getLastName().length()<3){
            throw new IllegalArgumentException("Last name must have at least 3 characters");
        }
        validateCpf(user.getCpf());
        if(userRepository.findByCpf(user.getCpf())!=null){
            throw new IllegalArgumentException("CPF already registered");
        }
        if(userRepository.findByEmail(user.getEmail())!=null){
            throw new IllegalArgumentException("Email already registered");
        }

        if(!user.isActive()){
            throw new IllegalArgumentException("Active must be true or false");
        }
    }

    //TODO: Implementar validação de login
    public void validateLogin(LoginRequestDto loginRequestDto) {
        validateEmail(loginRequestDto.getEmail());
        validatePassword(loginRequestDto.getPassword());
        var loginAttempt= userRepository.findByEmail(loginRequestDto.getEmail());


    }



    public void validateCpf(String cpf) {
        cpf=cpf.replaceAll("[.-]","");
        if(cpf.length()!=11){
            throw new IllegalArgumentException("CPF must have 11 characters");
        }
        if(!cpf.matches("\\d+")){
            throw new IllegalArgumentException("CPF must have only numbers");
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
            throw new IllegalArgumentException("Invalid CPF");
        }
    }

    public void validatePassword(String newPassword) {
        if(newPassword.replaceAll(" ","").length()<6){
            throw new IllegalArgumentException("Password must have at least 6 characters");
        }
    }
    public void  validateEmail(String email){
        if(email.matches("^(.+)@(.+)$")){
            throw new IllegalArgumentException("Invalid email");
    }

    }
}
