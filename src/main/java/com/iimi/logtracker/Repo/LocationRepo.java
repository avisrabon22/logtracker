package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<LocationModel,Long> {
}
