package com.compassuol.sp.challenge.msuser.controller;


import com.compassuol.sp.challenge.msuser.dto.UserStateDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserCreateDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserResponseDto;
import com.compassuol.sp.challenge.msuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//TODO: REVIEW SERVICES AND VALIDATORS

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/users")
    public ResponseEntity<UserResponseDto> createPerson(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDto));
    }

    @GetMapping("/v1/users/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @PutMapping("/v1/users/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody String newPassword) {
        userService.changePassword(id, newPassword);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    //TODO: VALIDATE UserStateDto
    @PutMapping("/v1/users/{id}")
    public void changeUserState(@PathVariable Long id,@RequestBody UserStateDto userStateDto) {
        userService.changeUserState(id, userStateDto.isActive());
        
    }
}
