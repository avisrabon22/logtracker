package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.LoginRequestDto;
import com.iimi.logtracker.DTOs.UserRequestDto;
import com.iimi.logtracker.DTOs.UserSignupResponseDto;
import com.iimi.logtracker.DTOs.UsersResponseDto;
import com.iimi.logtracker.Exception.AlreadyExist;
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
    public UserSignupResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Override
    public UserSignupResponseDto signup(UserRequestDto userRequestDto) throws AlreadyExist {
        Optional<UserModel> user=userRepo.findByUserName(userRequestDto.getUsername());
        if(user.isPresent()){
           throw new AlreadyExist("User already exist");
       }
        UserModel userModel = new UserModel();
        UserSignupResponseDto userSignupResponseDto = new UserSignupResponseDto();
//        *********************************************
        userModel.setUserName(userRequestDto.getUsername());
        userModel.setPassword(userRequestDto.getPassword());
//       **********************************************
        try{
            Optional<RoleModel> role = roleRepo.findByRoleName(userRequestDto.getRole());
            List<RoleModel> roles = new ArrayList<>();

            if (role.isPresent()) {
                RoleModel roleModel = new RoleModel();
                roleModel.setId(role.get().getId());
                roleModel.setRoleName(role.get().getRoleName());
                roles.add(roleModel);
            }
            userModel.setRole(roles);
            userRepo.save(userModel);
            userSignupResponseDto.setUsername(userRequestDto.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userSignupResponseDto;
    }

    @Override
    public List<UsersResponseDto> getUsers() {
        List<UserModel> users = userRepo.findAll();

        List<UsersResponseDto> userResponseDtos = new ArrayList<>();
        if (!users.isEmpty()) {
            for (UserModel user : users) {
                 UsersResponseDto usersResponseDto = new UsersResponseDto();
                 usersResponseDto.setId(user.getId());
                usersResponseDto.setUsername(user.getUserName());
                usersResponseDto.setRole(user.getRole().getFirst().getRoleName());
                userResponseDtos.add(usersResponseDto);
            }
            System.out.println(userResponseDtos);
            return userResponseDtos;
        }
        return userResponseDtos;
    }


}
