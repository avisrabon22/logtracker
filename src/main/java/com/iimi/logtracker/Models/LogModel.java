package com.iimi.logtracker.Models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@Entity
public class LogModel extends BaseModel {
    @Column(name = "username") // Optional: Specify column name if different from field name
    private String username;
    @Column(name = "device_name")
    private String device_name;
    @Column(name = "log_id")
    private Long log_id;
    @Column(name = "log_date", columnDefinition = "DATE")
    private LocalDate log_date;
    @Column(name = "log_time", columnDefinition = "TIME")
    private LocalTime log_time;
    @Column(name = "ip_address")
    private  String IPAddress;
}
