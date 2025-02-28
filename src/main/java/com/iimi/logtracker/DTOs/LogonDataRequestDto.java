package com.iimi.logtracker.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogonDataRequestDto {
    private String userName;
    private String eventID;
    private String timeCreated;
    private String dateCreated;
    private String ipAddress;
    private String computerName;
}
