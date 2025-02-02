package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.RoleRequestDto;
import com.iimi.logtracker.DTOs.RoleResponseDto;
import java.util.List;

public interface RoleInterface {
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) throws Exception;
    public List<RoleResponseDto> getRoles() throws Exception;
    public RoleResponseDto getRole(String roleName) throws Exception;
    public RoleResponseDto updateRole(RoleRequestDto roleRequestDto) throws Exception;
    public RoleResponseDto deleteRole(String roleName) throws Exception;
}
