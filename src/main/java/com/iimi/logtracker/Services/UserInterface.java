package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.*;
import com.iimi.logtracker.Exception.AlreadyExist;
import com.iimi.logtracker.Exception.NotFound;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserInterface {
   public UserSignupResponseDto login(@RequestBody LoginRequestDto loginRequestDto);
    public UserSignupResponseDto signup(@RequestBody UserRequestDto userRequestDto) throws AlreadyExist;
    public List<UsersResponseDto> getUsers() throws NotFound;
    public GetUserResponseDto getUser(GetUserRequestDto getUserRequestDto) throws NotFound;
    public UserSignupResponseDto deleteUser(Long id) throws NotFound;
    public UserUpdateResponseDto updateUSer(UserUpdateRequestDto userUpdateRequestDto) throws NotFound;
}
