package com.iimi.logtracker.Services;


import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Models.LogModel;
import com.iimi.logtracker.Repo.LogRepo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@CommonsLog
@Service
public class LogService implements LogInterface {
    private final LogRepo logRepo;

    public LogService(LogRepo logRepo) {
        this.logRepo = logRepo;
    }

    @Override
    public List<LogResponseDto> getLogs() {
        try {
            List<LogModel> logResponseDtos = logRepo.findAll();
            return logResponseDtos.stream().map(this::convertToDto).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error in fetching logs");
        }

    }

    @Override
    public void addLogs(LogRequestDto logRequestDto) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); // Combined format
        try {
            LogModel logModel = new LogModel();
            logModel.setUsername(logRequestDto.getUsername());
            logModel.setDevice_name(logRequestDto.getDevice_name());
            logModel.setDevice_type(logRequestDto.getDevice_type());
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate logDate = LocalDate.parse(logRequestDto.getLog_date(), dateFormatter);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime logTime = LocalTime.parse(logRequestDto.getLog_time(), timeFormatter);

            logModel.setLog_date(logDate);
            logModel.setLog_time(logTime);

            logRepo.save(logModel);
        }catch (DateTimeException e){
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("Error in adding logs");
        }
    }


    private LogResponseDto convertToDto(LogModel logModel) {
        LogResponseDto logResponseDto = new LogResponseDto();
        logResponseDto.setId(logModel.getId());
        logResponseDto.setUsername(logModel.getUsername());
        logResponseDto.setDevice_name(logModel.getDevice_name());
        logResponseDto.setLog_date(logModel.getLog_date());
        logResponseDto.setLog_time(logModel.getLog_time());
        logResponseDto.setDevice_type(logModel.getDevice_type());
        return logResponseDto;
    }
}
