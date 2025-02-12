package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.LoginRequestDto;
import com.iimi.logtracker.DTOs.UserRequestDto;
import com.iimi.logtracker.DTOs.UserSignupResponseDto;
import com.iimi.logtracker.DTOs.UsersResponseDto;
import com.iimi.logtracker.Exception.AlreadyExist;
import com.iimi.logtracker.Exception.NotFound;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserInterface {
   public UserSignupResponseDto login(@RequestBody LoginRequestDto loginRequestDto);
    public UserSignupResponseDto signup(@RequestBody UserRequestDto userRequestDto) throws AlreadyExist;
    public List<UsersResponseDto> getUsers();
    public UserSignupResponseDto deleteUser(Long id) throws NotFound;
}
