package com.iimi.logtracker.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class LogRequestVTwoDto {
    private String userName;
    private String deviceName;
    private String eventId;
    private String eventTime;
    private String eventDate;
    private String ipAddress;
    private String eventDescription;
    private String logName;
    private String machineName;
    private String userId;
    private String levelDisplayName;
    private String providerName;
    private String taskDisplayName;
}
