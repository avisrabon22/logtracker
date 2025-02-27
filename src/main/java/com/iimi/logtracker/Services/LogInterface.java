package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.*;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.DTOs.LogonDataRequestDto;

import java.util.List;

public interface LogInterface {
    List<LogResponseDto> getLogs() throws NotFound;
    void addLogs(LogRequestDto logRequestDto);
    List<LogResponseDto> searchByLogId(LogIdSearchRequestDto logIdSearchRequestDto) throws NotFound;
    void addLogsVTwo(LogRequestVTwoDto logRequestVTwoDto) throws Exception;
    List<LogResponseVTwoDto> getLogsVTwo(LogRequestVTwoDto logRequestVTwoDto);
    void addLogonData(LogonDataRequestDto logonDataRequestDto) throws Exception;

}
