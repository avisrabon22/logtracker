package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.RoleRequestDto;
import com.iimi.logtracker.DTOs.RoleResponseDto;
import com.iimi.logtracker.DTOs.UpdateRoleRequestDto;

import java.util.List;

public interface RoleInterface {
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) throws Exception;
    public List<RoleResponseDto> getRoles() throws Exception;
    public RoleResponseDto getRole(Long id) throws Exception;
    public RoleResponseDto updateRole(UpdateRoleRequestDto updateRoleRequestDto) throws Exception;
    public RoleResponseDto deleteRole(Long id) throws Exception;
}
