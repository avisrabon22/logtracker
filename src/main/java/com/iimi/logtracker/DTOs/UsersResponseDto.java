package com.iimi.logtracker.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersResponseDto {
    private Long id;
    private String username;
    private String role;
}
