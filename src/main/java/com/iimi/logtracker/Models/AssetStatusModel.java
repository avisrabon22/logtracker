package com.iimi.logtracker.Models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AssetStatusModel extends BaseModel {
    private String assetStatus;
    private String assetStatusDescription;
}
