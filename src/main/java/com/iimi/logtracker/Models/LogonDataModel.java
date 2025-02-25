package com.iimi.logtracker.Models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class LogonDataModel extends BaseModel {
    private String computerName;
    private Long eventID;
    private String userName;
    private String ipAddress;
    private LocalDate dateCreated;
    private LocalTime timeCreated;

}
