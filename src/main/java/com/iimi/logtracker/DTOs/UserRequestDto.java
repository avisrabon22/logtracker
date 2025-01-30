package com.iimi.logtracker.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDto {
    private String username;
    private String password;
    private String role;
}
