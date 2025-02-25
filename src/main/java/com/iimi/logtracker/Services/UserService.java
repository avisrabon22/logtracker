package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.*;
import com.iimi.logtracker.Exception.AlreadyExist;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Models.RoleModel;
import com.iimi.logtracker.Models.UserModel;
import com.iimi.logtracker.Repo.RoleRepo;
import com.iimi.logtracker.Repo.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

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
            Optional<RoleModel> role = roleRepo.findByRoleName("ROLE_"+userRequestDto.getRole().toUpperCase());
            if (role.isPresent()) {
                RoleModel roleModel = new RoleModel();
                roleModel.setId(role.get().getId());
                roleModel.setRoleName(role.get().getRoleName());
                userModel.setRole(roleModel);
            }

            userRepo.save(userModel);
            userSignupResponseDto.setUsername(userRequestDto.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userSignupResponseDto;
    }

    @Override
    public List<UsersResponseDto> getUsers() throws NotFound {
        List<UserModel> users=new ArrayList<>();
        try{
             users = userRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<UsersResponseDto> userResponseDtos = new ArrayList<>();
        if(users.isEmpty())
        {
            throw new NotFound("No data found!");
        }

            for (UserModel user : users) {
                 UsersResponseDto usersResponseDto = new UsersResponseDto();
                 usersResponseDto.setId(user.getId());
                usersResponseDto.setUsername(user.getUserName());
                usersResponseDto.setRole(user.getRole().getRoleName().substring(5));
                userResponseDtos.add(usersResponseDto);
            }
            return userResponseDtos;

    }

    @Override
    public GetUserResponseDto getUser(GetUserRequestDto getUserRequestDto) throws NotFound {
        Optional<UserModel> userModel = userRepo.findById(getUserRequestDto.getId());
         if (userModel.isEmpty())
             throw new NotFound("No such user in store!");

         GetUserResponseDto getUserResponseDto=new GetUserResponseDto();
         getUserResponseDto.setUsername(userModel.get().getUserName());
         getUserResponseDto.setRole(userModel.get().getRole().getRoleName().substring(5));
        return getUserResponseDto;
    }

    @Override
    public UserSignupResponseDto deleteUser(Long id) throws NotFound {
        Optional<UserModel> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(id);
            UserSignupResponseDto userSignupResponseDto = new UserSignupResponseDto();
            userSignupResponseDto.setUsername(user.get().getUserName());
            return userSignupResponseDto;
        }
      throw new NotFound("User not found");
    }

    @Override
    public UserUpdateResponseDto updateUSer(UserUpdateRequestDto userUpdateRequestDto) throws NotFound {
        UserUpdateResponseDto userUpdateResponseDto = new UserUpdateResponseDto();
        System.out.println(userUpdateRequestDto.getRole());
        Optional<UserModel> user = userRepo.findById(userUpdateRequestDto.getId());
        if (user.isPresent()){
            UserModel userModel = new UserModel();
            userModel.setId(userUpdateRequestDto.getId());
            userModel.setUserName(userUpdateRequestDto.getUsername());
            userModel.setPassword(userUpdateRequestDto.getPassword());
            Optional<RoleModel> role=roleRepo.findByRoleName("ROLE_"+userUpdateRequestDto.getRole());
            if (role.isPresent()){
                RoleModel roleModel = new RoleModel();
                roleModel.setRoleName(role.get().getRoleName());
                userModel.setRole(roleModel);
            }
            userRepo.save(userModel);
        }
        else {
            throw new NotFound("No user found!");
        }
        user.ifPresent(userModel -> userUpdateResponseDto.setUsername(userModel.getUserName()));

        return userUpdateResponseDto;
    }


}
