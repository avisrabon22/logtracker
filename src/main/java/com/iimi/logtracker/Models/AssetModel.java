package com.iimi.logtracker.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class AssetModel extends BaseModel{
    @Column(nullable = false)
    private String assetName;
    private String assetDescription;
    private BigDecimal assetPrice;
    private Date assetAssignDate;
    private Date assetPurchaseDate;
    private String assetSerialNumber;

}
