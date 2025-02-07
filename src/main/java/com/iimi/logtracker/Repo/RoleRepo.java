package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel,Long> {
    Optional<RoleModel> findByRoleName(String roleName);
    RoleModel deleteByRoleName(String roleName);
}
