package com.iimi.logtracker.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class LogModelVTwo extends BaseModel{
    private String userName;
    private String deviceName;
    private Long eventId;
    private LocalTime eventTime;
    private LocalDate eventDate;
    private String ipAddress;
    private String eventDescription;
    private String logName;
    private String machineName;
    private String userId;
    private String levelDisplayName;
    private String providerName;
    private String taskDisplayName;
}
