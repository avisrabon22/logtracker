package com.iimi.logtracker.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(nullable = false)
    private String assetSerialNumber;
    private Date lastMaintenanceDate;
    @ManyToOne
    @JoinColumn(name = "asset_type_id_id")
    private AssetTypeModel assetTypeId;
    @ManyToOne
    @JoinColumn(name = "location_id_id")
    private LocationModel locationId;
    @ManyToOne
    @JoinColumn(name = "asset_status_id_id")
    private AssetStatusModel assetStatusId;
}
