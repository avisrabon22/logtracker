package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel,Long> {
    RoleModel findByRole(String role);
}
