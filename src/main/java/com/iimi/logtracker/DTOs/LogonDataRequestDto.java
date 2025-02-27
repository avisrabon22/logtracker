package com.iimi.logtracker.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogonDataRequestDto {
    private String userName;
    private String eventID;
    private String computerName;
    private String dateCreated;
    private String timeCreated;
    private String ipAddress;
}
