package com.iimi.logtracker.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogResponseDto {
    private Long id;
    private String userName;
    private String deviceName;
    private String eventId;
    private String eventTime;
    private String eventDate;
    private String  ipAddress;
}
