package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.LoginRequestDto;
import com.iimi.logtracker.DTOs.UserRequestDto;
import com.iimi.logtracker.DTOs.UserResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserInterface {
   public UserResponseDto login(@RequestBody LoginRequestDto loginRequestDto);
    public UserResponseDto signup(@RequestBody UserRequestDto userRequestDto);
}
