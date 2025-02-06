package com.iimi.logtracker.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoleRequestDto {
    private Long id;
    private String roleName;
}
