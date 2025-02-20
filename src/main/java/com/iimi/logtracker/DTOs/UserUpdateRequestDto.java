package com.iimi.logtracker.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {
    private Long id;
    private String username;
    private String password;
    private String role;
}
