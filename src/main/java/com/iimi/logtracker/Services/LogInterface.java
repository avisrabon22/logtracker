package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;

import java.util.List;

public interface LogInterface {
    String addLog(LogRequestDto logRequestDto);
    List<LogResponseDto> getLogs();

}
