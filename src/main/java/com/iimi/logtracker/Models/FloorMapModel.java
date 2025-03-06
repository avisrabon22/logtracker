package com.iimi.logtracker.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FloorMapModel extends BaseModel{
    private String cpuId;
    private String displayId;
    private String keyboardId;
    private String mouseId;
}
