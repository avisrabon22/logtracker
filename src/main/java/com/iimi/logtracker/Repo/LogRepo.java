package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.LogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo extends JpaRepository<LogModel,Long> {
}
