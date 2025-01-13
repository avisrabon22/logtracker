package com.iimi.logtracker.Services;


import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Models.LogModel;
import com.iimi.logtracker.Repo.LogRepo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;

@CommonsLog
@Service
public class LogService implements LogInterface{
    private final LogRepo logRepo;

    public LogService(LogRepo logRepo) {
        this.logRepo = logRepo;
    }

    @Override
    public String addLog(LogRequestDto logRequestDto) {
      return null;
    }

    @Override
    public List<LogResponseDto> getLogs() {
        try {
            List<LogModel> logResponseDtos=logRepo.findAll();
            return logResponseDtos.stream().map(this::convertToDto).toList();
        }
        catch (Exception e){
           throw new RuntimeException("Error in fetching logs");
        }

    }



    private LogResponseDto convertToDto(LogModel logModel){
                LogResponseDto logResponseDto=new LogResponseDto();
                logResponseDto.setId(logModel.getId());
                logResponseDto.setUsername(logModel.getUsername());
                logResponseDto.setDevice_name(logModel.getDevice_name());
                logResponseDto.setLog_date(logModel.getLog_date());
                logResponseDto.setLog_time(logModel.getLog_time());
                logResponseDto.setDevice_type(logModel.getDevice_type());
                return logResponseDto;
    }
}
