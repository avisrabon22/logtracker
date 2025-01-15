package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;

import java.util.List;

public interface LogInterface {
    List<LogResponseDto> getLogs();
    void addLogs(LogRequestDto logRequestDto);

}
