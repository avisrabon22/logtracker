package com.iimi.logtracker.Services;


import com.iimi.logtracker.DTOs.LogIdSearchRequestDto;
import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Exception.NotFound;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CommonsLog
@Service
public class LogService implements LogInterface {
    private final LogRepo logRepo;

    public LogService(LogRepo logRepo) {
        this.logRepo = logRepo;
    }
//    get logs *****************************************************************
    @Override
    public List<LogResponseDto> getLogs() {
        try {
            List<LogModel> logResponseDtos = logRepo.findAll();
            return logResponseDtos.stream().map(this::convertToDto).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error in fetching logs");
        }

    }
//    add logs *****************************************************************
    @Override
    public void addLogs(LogRequestDto logRequestDto) {
        try {
            LogModel logModel = new LogModel();
            logModel.setUsername(logRequestDto.getUsername());
            logModel.setDevice_name(logRequestDto.getDevice_name());
            logModel.setDevice_type(logRequestDto.getDevice_type());
            logModel.setLog_id(logRequestDto.getLog_id());
//            Set date and time
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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

//    search by log id *****************************************************************
    @Override
    public List<LogResponseDto> searchByLogId(LogIdSearchRequestDto logIdSearchRequestDto) {
        List<LogResponseDto> logResponseDtoList = new ArrayList<>();
        try {
            Optional<List<LogModel>> logModelList =logRepo.findByLogId(logIdSearchRequestDto.getLogId());
            if(logModelList.isPresent()){
                for (LogModel logModelResponse:logModelList.get()){
                    LogResponseDto logResponseDto = getLogResponseDto(logModelResponse);
                    logResponseDtoList.add(logResponseDto);
                }
            }
            return logResponseDtoList;
        }catch (NotFound e){
            throw  new NotFound("No such id found!!");

        }
    }
// setup the response dto of log for search by log id *****************************************************************
    private static LogResponseDto getLogResponseDto(LogModel logModelResponse) {
        LogResponseDto logResponseDto = new LogResponseDto();
        logResponseDto.setId(logModelResponse.getLog_id());
        logResponseDto.setUsername(logModelResponse.getUsername());
        logResponseDto.setDevice_name(logModelResponse.getDevice_name());
        logResponseDto.setDevice_type(logModelResponse.getDevice_type());
        logResponseDto.setLog_id(logModelResponse.getLog_id());
        logResponseDto.setLog_date(logModelResponse.getLog_date());
        logResponseDto.setLog_time(logModelResponse.getLog_time());
        return logResponseDto;
    }
// convert log model to log response dto *****************************************************************
    private LogResponseDto convertToDto(LogModel logModel) {
        LogResponseDto logResponseDto = new LogResponseDto();
        logResponseDto.setId(logModel.getId());
        logResponseDto.setUsername(logModel.getUsername());
        logResponseDto.setDevice_name(logModel.getDevice_name());
        logResponseDto.setDevice_type(logModel.getDevice_type());
        logResponseDto.setLog_id(logModel.getLog_id());
        logResponseDto.setLog_date(logModel.getLog_date());
        logResponseDto.setLog_time(logModel.getLog_time());
        return logResponseDto;
    }
}
