package com.iimi.logtracker.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogRequestDto {
    private String Username;
    private String DeviceName;
    private String EventID;
    private String EventTime;
    private String EventDate;
    private String  IPAddress;
}
