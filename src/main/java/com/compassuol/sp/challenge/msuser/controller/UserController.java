package com.compassuol.sp.challenge.msuser.controller;


import com.compassuol.sp.challenge.msuser.dto.LoginRequestDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserCreateDto;
import com.compassuol.sp.challenge.msuser.dto.user.UserResponseDto;
import com.compassuol.sp.challenge.msuser.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//TODO: REVIEW SERVICES AND VALIDATORS

@RestController
public class UserController {
    private UserService userService;

    @PostMapping("/v1/users")
    public ResponseEntity<UserResponseDto> createPerson(@RequestBody UserCreateDto personRequestDto) {
        return ResponseEntity.ok(userService.createPerson(personRequestDto));
    }

    @GetMapping("/v1/users/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    //TODO: Fix service AND validator
    @PutMapping("/v1/users/{id}/password")
    public void changePassword(@RequestParam Long id, @RequestBody String newPassword) {
        userService.changePassword(id, newPassword);
    }
    @PutMapping("/v1/users/{id}")
    public void changeUserState(@RequestParam Long id,@RequestBody boolean active) {
        userService.changeUserState(id, active);
        
    }
}
