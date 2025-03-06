package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.FloorMapModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorMapRepo extends JpaRepository<FloorMapModel,Long> {
}
