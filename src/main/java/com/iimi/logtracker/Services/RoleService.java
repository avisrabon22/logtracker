package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.RoleRequestDto;
import com.iimi.logtracker.DTOs.RoleResponseDto;
import com.iimi.logtracker.Exception.AlreadyExist;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Models.RoleModel;
import com.iimi.logtracker.Repo.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements RoleInterface{
    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) throws Exception {
        try {
            RoleModel roleModel = new RoleModel();
            roleModel.setRole(roleRequestDto.getRole());
            RoleModel getRole=roleRepo.findByRole(roleRequestDto.getRole());
            if (roleModel.equals(getRole)) {
                throw new AlreadyExist("Role already exists");
            }
            roleRepo.save(roleModel);
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            roleResponseDto.setRoleName(roleModel.getRole());
            return roleResponseDto;
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    @Override
    public List<RoleResponseDto> getRoles() throws Exception {
        try{
            List<RoleModel> roleModelList=roleRepo.findAll();
            if(roleModelList.isEmpty()){
                throw new NotFound("No roles found");
            }
            List<RoleResponseDto> roleResponseDtoList = new ArrayList<>();
            for (RoleModel roleModel : roleModelList) {
                RoleResponseDto roleResponseDto = new RoleResponseDto();
                roleResponseDto.setRoleName(roleModel.getRole());
                roleResponseDtoList.add(roleResponseDto);
            }
            return roleResponseDtoList;
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    @Override
    public RoleResponseDto getRole(String roleName) throws Exception {
        if (roleName.isBlank()) {
            throw new NotFound("Please select a role");
        }
        try
        {
            RoleModel role=roleRepo.findByRole(roleName);
            if (role.getRole().isEmpty())
            {
                throw new NotFound("Role not found");
            }
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            roleResponseDto.setRoleName(role.getRole());
            return roleResponseDto;
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    @Override
    public RoleResponseDto updateRole(RoleRequestDto roleRequestDto) throws Exception {
        try {
            RoleModel roleRepoByRole=roleRepo.findByRole(roleRequestDto.getRole());
            if (roleRepoByRole.getRole().isEmpty()) {
                throw new NotFound("Role not found");
            }
            roleRepoByRole.setRole(roleRequestDto.getRole());
            roleRepo.save(roleRepoByRole);
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            roleResponseDto.setRoleName(roleRepoByRole.getRole());
            return roleResponseDto;
          }
          catch (Exception e) {
              throw new Exception("Something went wrong");
          }
    }

    @Override
    public RoleResponseDto deleteRole(String roleName) throws Exception {
        try
        {
            RoleModel getRole=roleRepo.findByRole(roleName);
            if (getRole.getRole().isEmpty())
            {
                throw new NotFound("Role not found");
            }
            RoleModel roleModel=roleRepo.deleteByRoleName(roleName);
            if (roleModel.getRole().isEmpty())
            {
                throw new NotFound("Role not deleted");
            }
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            roleResponseDto.setRoleName(roleModel.getRole());
            return roleResponseDto;
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }
}
