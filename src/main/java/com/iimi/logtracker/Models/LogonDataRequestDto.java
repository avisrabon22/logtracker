package com.iimi.logtracker.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogonDataRequestDto {
    private String computerName;
    private String eventID;
    private String timeCreated;
    private String userName;
    private String ipAddress;
    private String dateCreated;
}
