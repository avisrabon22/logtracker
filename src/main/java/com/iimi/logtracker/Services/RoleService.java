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
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) throws Exception, AlreadyExist {
        String roleName = roleRequestDto.getRoleName();
        RoleModel roleModel = new RoleModel();
        Optional<RoleModel> getRole=roleRepo.findByRoleName("ROLE_"+roleName.toUpperCase());
        if (getRole.isPresent())
        {
            throw new AlreadyExist("Role already exists");
        }
        roleModel.setRoleName("ROLE_"+roleName.toUpperCase());
        try{
            roleRepo.save(roleModel);
        }
        catch (Exception e){
            throw new Exception("Data not submitted!");
        }

        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setRoleName(roleModel.getRoleName().substring(5));
        return roleResponseDto;

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

            roleResponseDto.setRoleName(roleModel.getRoleName().substring(5));
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
            roleResponseDto.setRoleName(role.get().getRoleName().substring(5));
            return roleResponseDto;
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    @Override
    public RoleResponseDto updateRole(UpdateRoleRequestDto updateRoleRequestDto) throws Exception {
        RoleModel role = new RoleModel();
        if (updateRoleRequestDto.getId()==null || updateRoleRequestDto.getRoleName().isEmpty()) {
            throw new NotFound("Not getting a role name or id");
        }
// get data from database *************************************************************
            Optional<RoleModel> roleModel=roleRepo.findById(updateRoleRequestDto.getId());
            if (roleModel.isPresent())
            {
                role.setId(updateRoleRequestDto.getId());
                role.setRoleName("ROLE_"+updateRoleRequestDto.getRoleName().toUpperCase());
            }
            else {
                throw new NotFound("Role not found!");
            }
//            Save data in db *************************************
        try {
            roleRepo.save(role);
        }
        catch(Exception e){
            throw new Exception("Data not updated!");
        }

        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setId(role.getId());
        roleResponseDto.setRoleName(role.getRoleName().substring(5));
        return roleResponseDto;

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
