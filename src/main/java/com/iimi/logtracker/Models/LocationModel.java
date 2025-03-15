package com.iimi.logtracker.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LocationModel extends BaseModel {
    private String locationName;
    private String locationDescription;
}
