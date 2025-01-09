package com.iimi.logtracker.Models;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Setter
@Getter
@Entity
public class LogModel extends BaseModel {
    private String username;
    private  String device_name;
    private String device_type;
    private Date log_date;
    private Time log_time;
}
