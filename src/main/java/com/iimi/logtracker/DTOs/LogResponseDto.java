package com.iimi.logtracker.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class LogResponseDto {
    private Long id;
    private String username;
    private String device_name;
    private String device_type;
    private Long log_id;
    private LocalDate log_date;
    private LocalTime log_time;
}
