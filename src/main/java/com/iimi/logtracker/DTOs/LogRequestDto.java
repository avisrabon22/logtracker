package com.iimi.logtracker.DTOs;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class LogRequestDto {
    private String username;
    private String device_name;
    private String device_type;
    private String log_date;
    private String log_time;
}
