package com.compassuol.sp.challenge.msuser.controller;

import com.compassuol.sp.challenge.msuser.dto.userDto.LoginRequestDto;
import com.compassuol.sp.challenge.msuser.exception.ErrorMessage;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.UserValidationException;
import com.compassuol.sp.challenge.msuser.jwt.JwtToken;
import com.compassuol.sp.challenge.msuser.jwt.JwtUserDetailsService;
import com.compassuol.sp.challenge.msuser.service.PublisherService;
import com.compassuol.sp.challenge.msuser.service.ValidatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Authentication",description = "Recursos para autenticação de usuários na API.")
@RestController
@RequestMapping()
public class AuthenticationController {

    @Autowired
    private  JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public ValidatorService validatorService;

    @Autowired
    private PublisherService publisherService;


    @Operation(summary = "Realiza o login do usuário.", description = "Recurso para realizar o login do usuário na API.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtToken.class))),
                    @ApiResponse(responseCode = "422", description = "Usuário ou senha inválidos.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PostMapping("/v1/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        validatorService.validateLogin(loginRequestDto);
        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = jwtUserDetailsService.getTokenAutheticated(loginRequestDto.getEmail());
            publisherService.sendNotification(loginRequestDto.getEmail(),"LOGIN");
            return ResponseEntity.ok(token);

        }catch (AuthenticationException e){
            throw new UserValidationException("Invalid username or password");
        }

    }

}
