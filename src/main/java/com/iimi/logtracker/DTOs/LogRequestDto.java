package com.iimi.logtracker.DTOs;


import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class LogRequestDto {
    private String username;
    private  String device_name;
    private String device_type;
    private Date log_date;
    private Time log_time;
}
