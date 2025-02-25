package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.LogonDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogonDataRepo extends JpaRepository<LogonDataModel,Long> {
}
