package com.iimi.logtracker.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AssetTypeModel extends BaseModel{
    private String assetType;
    private String assetTypeDescription;
}
