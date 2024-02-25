package com.compassuol.sp.challenge.msuser.controller;

import com.compassuol.sp.challenge.msuser.dto.LoginRequestDto;
import com.compassuol.sp.challenge.msuser.exception.ErrorMessage;
import com.compassuol.sp.challenge.msuser.jwt.JwtToken;
import com.compassuol.sp.challenge.msuser.jwt.JwtUserDetailsService;
import com.compassuol.sp.challenge.msuser.service.ValidatorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AuthenticationController {

    @Autowired
    private  JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public ValidatorService validatorService;







    @PostMapping("/v1/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        System.out.println("Login with username " + loginRequestDto.getEmail());
        validatorService.validateLogin(loginRequestDto);
        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = jwtUserDetailsService.getTokenAutheticated(loginRequestDto.getEmail());
            return ResponseEntity.ok(token);

        }catch (AuthenticationException e){
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Invalid username or password"));

        // return ResponseEntity.ok(userService.login(loginRequestDto));
    }

}
