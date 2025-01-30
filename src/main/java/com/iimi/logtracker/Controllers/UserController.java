package com.iimi.logtracker.Controllers;

import com.iimi.logtracker.DTOs.LoginRequestDto;
import com.iimi.logtracker.DTOs.LoginResponseDto;
import com.iimi.logtracker.DTOs.UserRequestDto;
import com.iimi.logtracker.DTOs.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody  LoginRequestDto loginRequestDto){
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody  UserRequestDto userRequestDto){

        return ResponseEntity.ok().body("User created successfully "+userRequestDto.getUsername());
    }

}
