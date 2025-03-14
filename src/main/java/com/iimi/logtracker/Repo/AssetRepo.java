package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.AssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepo extends JpaRepository<AssetModel,Long> {
}
