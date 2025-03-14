package com.iimi.logtracker.Repo;

import com.iimi.logtracker.Models.AssetModel;
import com.iimi.logtracker.Models.AssetTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTypeRepo extends JpaRepository<AssetTypeModel,Long> {
}
