package com.iimi.logtracker.Repo;

import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Models.LogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogRepo extends JpaRepository<LogModel,Long> {
    @Query("SELECT l FROM LogModel l WHERE l.log_id = :logId")
    Optional<List<LogModel>>  findByLogId(@Param("logId") Long logId);
}
