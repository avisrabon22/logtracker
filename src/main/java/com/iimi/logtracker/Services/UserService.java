package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.LoginRequestDto;
import com.iimi.logtracker.DTOs.UserRequestDto;
import com.iimi.logtracker.DTOs.UserResponseDto;
import com.iimi.logtracker.Models.RoleModel;
import com.iimi.logtracker.Models.UserModel;
import com.iimi.logtracker.Repo.RoleRepo;
import com.iimi.logtracker.Repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserInterface {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }


    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto signup(UserRequestDto userRequestDto) {
        UserModel userModel = new UserModel();
        userModel.setUserName(userRequestDto.getUsername());
        userModel.setPassword(userModel.getPassword());
        UserResponseDto userResponseDto = new UserResponseDto();

        try{

            Optional<RoleModel> role = roleRepo.findByRoleName(userRequestDto.getRole());
            List<RoleModel> roles = new ArrayList<>();

            if (role.isPresent()) {
                role.get().setRoleName(userRequestDto.getRole());
                roles.add(role.get());
            }
            userModel.setRole(roles);
            userRepo.save(userModel);
            userResponseDto.setUsername(userResponseDto.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userResponseDto;
    }
}
