package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Long> {

}
