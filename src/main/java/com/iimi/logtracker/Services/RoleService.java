package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.RoleRequestDto;
import com.iimi.logtracker.DTOs.RoleResponseDto;
import com.iimi.logtracker.DTOs.UpdateRoleRequestDto;
import com.iimi.logtracker.Exception.AlreadyExist;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Models.RoleModel;
import com.iimi.logtracker.Repo.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            RoleModel getRole=roleRepo.findByRoleName("ROLE_"+roleRequestDto.getRoleName().toUpperCase());
            if (roleModel.equals(getRole)) {
                throw new AlreadyExist("Role already exists");
            }
            roleModel.setRoleName("ROLE_"+roleRequestDto.getRoleName().toUpperCase());
            roleRepo.save(roleModel);

            RoleResponseDto roleResponseDto = new RoleResponseDto();
            roleResponseDto.setRoleName(roleModel.getRoleName());
            return roleResponseDto;
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    @Override
    public List<RoleResponseDto> getRoles() throws Exception {
            List<RoleModel> roleModelList=roleRepo.findAll();
            if(roleModelList.isEmpty()){
                throw new NotFound("No roles found");
            }
            List<RoleResponseDto> roleResponseDtoList = new ArrayList<>();
            for (RoleModel roleModel : roleModelList) {
                RoleResponseDto roleResponseDto = new RoleResponseDto();
                roleResponseDto.setId(roleModel.getId());
                roleResponseDto.setRoleName(roleModel.getRoleName());
                roleResponseDtoList.add(roleResponseDto);
            }
            return roleResponseDtoList;

    }

    @Override
    public RoleResponseDto getRole(Long id) throws Exception {
        if (id==null) {
            throw new NotFound("Please select a role");
        }
        try
        {
            Optional<RoleModel> role=roleRepo.findById(id);
            if (role.isEmpty())
            {
                throw new NotFound("Role not found");
            }
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            roleResponseDto.setId(role.get().getId());
            roleResponseDto.setRoleName(role.get().getRoleName());
            return roleResponseDto;
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    @Override
    public RoleResponseDto updateRole(UpdateRoleRequestDto updateRoleRequestDto) throws Exception {
        if (updateRoleRequestDto.getId()==null) {
            throw new NotFound("Please enter a role name");
        }
        try {
            RoleModel roleModel=roleRepo.findById(updateRoleRequestDto.getId()).orElseThrow(()->new NotFound("Role not found"));
            roleModel.setId(updateRoleRequestDto.getId());
            roleModel.setRoleName("ROLE_"+updateRoleRequestDto.getRoleName().toUpperCase());
            roleRepo.save(roleModel);
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            roleResponseDto.setId(roleModel.getId());
            roleResponseDto.setRoleName(roleModel.getRoleName());
            return roleResponseDto;
          }
          catch (Exception e) {
              throw new Exception("Something went wrong");
          }
    }

    @Override
    public RoleResponseDto deleteRole(Long id) throws Exception {
        try
        {
            Optional<RoleModel> getRole=roleRepo.findById(id);
            if (getRole.isEmpty())
            {
                throw new NotFound("Role not found");
            }
             roleRepo.deleteById(id);
            RoleModel roleModel = getRole.get();
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            roleResponseDto.setRoleName(roleModel.getRoleName());
            return roleResponseDto;
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }
}
