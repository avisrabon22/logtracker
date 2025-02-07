package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.LogIdSearchRequestDto;
import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Exception.NotFound;

import java.util.List;

public interface LogInterface {
    List<LogResponseDto> getLogs();
    void addLogs(LogRequestDto logRequestDto);
    List<LogResponseDto> searchByLogId(LogIdSearchRequestDto logIdSearchRequestDto) throws NotFound;

}
