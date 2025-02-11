package com.iimi.logtracker.Controllers;

import com.iimi.logtracker.DTOs.LoginRequestDto;
import com.iimi.logtracker.DTOs.LoginResponseDto;
import com.iimi.logtracker.DTOs.UserRequestDto;
import com.iimi.logtracker.DTOs.UserResponseDto;
import com.iimi.logtracker.Exception.AlreadyExist;
import com.iimi.logtracker.Services.UserInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserInterface userInterface;

    public UserController(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody  LoginRequestDto loginRequestDto){
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody  UserRequestDto userRequestDto) throws AlreadyExist {
        if(userRequestDto.getUsername().isBlank() || userRequestDto.getPassword().isBlank()||userRequestDto.getRole().isBlank())
        {
            return ResponseEntity.ok().body("Please fill all filed!!");
        }
        UserResponseDto userResponseDto=userInterface.signup(userRequestDto);
        return ResponseEntity.ok().body(userResponseDto);
    }

}
