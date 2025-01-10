package com.iimi.logtracker.Services;


import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService implements LogInterface{
    @Override
    public String addLog(LogRequestDto logRequestDto) {
      return null;
    }

    @Override
    public List<LogResponseDto> getLogs() {

     return null;
    }

}
