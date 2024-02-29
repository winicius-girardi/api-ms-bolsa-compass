package com.compassuol.sp.challenge.msuser.controller;


import com.compassuol.sp.challenge.msuser.dto.userDto.PasswordChangeDto;
import com.compassuol.sp.challenge.msuser.dto.userDto.UserCreateDto;
import com.compassuol.sp.challenge.msuser.dto.userDto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.dto.userDto.UserStateDto;
import com.compassuol.sp.challenge.msuser.exception.ErrorMessage;
import com.compassuol.sp.challenge.msuser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//TODO: REVIEW SERVICES AND VALIDATORS

@Tag(name = "User", description = "Recursos para manipulação de usuários na API.")
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;



    @Operation(summary = "Cria um novo usuário.", description = "Recurso para criar um novo usuário na API.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "422", description = "Requisição contém campos inválidos.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "409", description = "Já existe um usuário com o email e/ou cpf informado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
            )
        }
    )
    @PostMapping("/v1/users")
    public ResponseEntity<UserResponseDto> createPerson(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDto));
    }




    @Operation(summary = "Busca um usuário pelo id.", description = "Recurso para recuperar um usuário pelo id.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Necesário autenticação.")
        }
    )
    @GetMapping("/v1/users/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }



    @Operation(summary = "Altera a senha de um usuário.", description = "Recurso para alterar a senha de um usuário na API.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Senha fornecida é invalida e/ou igual a atual.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Necesário autenticação.")
        }
    )
    @PutMapping("/v1/users/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody PasswordChangeDto newPassword) {
        userService.changePassword(id, newPassword);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }



    @Operation(summary = "Altera o estado de um usuário.", description = "Recurso para alterar o estado de um usuário na API.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Estado do usuário alterado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Necesário autenticação.")
        }
    )
    @PutMapping("/v1/users/{id}")
    public void changeUserState(@PathVariable Long id,@RequestBody UserStateDto userStateDto) {
        userService.changeUserState(id, userStateDto.isActive());
        
    }
}
