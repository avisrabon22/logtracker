package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.LogModelVTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogVTwoRepo extends JpaRepository<LogModelVTwo,Long> {
}
