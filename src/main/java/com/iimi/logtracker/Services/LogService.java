package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.*;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Models.LogModel;
import com.iimi.logtracker.Models.LogModelVTwo;
import com.iimi.logtracker.Models.LogonDataModel;
import com.iimi.logtracker.Models.LogonDataRequestDto;
import com.iimi.logtracker.Repo.LogRepo;
import com.iimi.logtracker.Repo.LogVTwoRepo;
import com.iimi.logtracker.Repo.LogonDataRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LogService implements LogInterface {
    private final LogRepo logRepo;
    private final LogVTwoRepo logVTwoRepo;
    private final LogonDataRepo logonDataRepo;


    public LogService(LogRepo logRepo, LogVTwoRepo logVTwoRepo, LogonDataRepo logonDataRepo) {
        this.logRepo = logRepo;
        this.logVTwoRepo = logVTwoRepo;
        this.logonDataRepo = logonDataRepo;
    }

    //    get logs *****************************************************************
    @Override
    public List<LogResponseDto> getLogs() throws NotFound {
        List<LogModel> logResponseDtos = new ArrayList<>();
        try {
            logResponseDtos = logRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error in fetching logs");
        }

        if (logResponseDtos.isEmpty()) throw new NotFound("No data found!");
        return logResponseDtos.stream().map(this::convertToDto).toList();


    }

    //    Get logs *****************************************************************
    @Override
    public List<LogResponseVTwoDto> getLogsVTwo(LogRequestVTwoDto logRequestVTwoDto) {
        return List.of();
    }

    //    add logs *****************************************************************
    @Override
    public void addLogs(LogRequestDto logRequestDto) {
        if (logRequestDto.getUserName() == null) throw new RuntimeException("Some value not getting");

        LogModel logModel = new LogModel();
//       Set all log model value to DB from system
        logModel.setUsername(logRequestDto.getUserName());
        logModel.setDevice_name(logRequestDto.getDeviceName());
        logModel.setLog_id(Long.parseLong(logRequestDto.getEventId()));
//            Set date
        logModel.setLog_date(LocalDate.parse(logRequestDto.getEventDate()));
//            Set time
        logModel.setLog_time(LocalTime.parse(logRequestDto.getEventTime()));
//        Set ip address
        logModel.setIPAddress(logRequestDto.getIpAddress());

        try {
            logRepo.save(logModel);
        } catch (Exception e) {
            throw new RuntimeException("Unable to add logs");
        }
    }

    //    Add logs v2
    @Override
    public void addLogsVTwo(LogRequestVTwoDto logRequestVTwoDto) throws Exception {
        if (logRequestVTwoDto.getUserName().isEmpty()) {
            throw new Exception("Data miss!!");
        }
        LogModelVTwo logModelVTwo = new LogModelVTwo();
        logModelVTwo.setUserName(logRequestVTwoDto.getUserName());
        logModelVTwo.setUserId(logRequestVTwoDto.getUserId());
        logModelVTwo.setEventId(Long.parseLong(logRequestVTwoDto.getEventId()));
        logModelVTwo.setLogName(logRequestVTwoDto.getLogName());
        logModelVTwo.setEventDescription(logRequestVTwoDto.getEventDescription());
        logModelVTwo.setEventDate(LocalDate.parse(logRequestVTwoDto.getEventDate()));
        logModelVTwo.setEventTime(LocalTime.parse(logRequestVTwoDto.getEventTime()));
        logModelVTwo.setDeviceName(logModelVTwo.getDeviceName());
        logModelVTwo.setMachineName(logModelVTwo.getMachineName());
        logModelVTwo.setProviderName(logModelVTwo.getProviderName());
        logModelVTwo.setIpAddress(logModelVTwo.getIpAddress());
        logModelVTwo.setTaskDisplayName(logRequestVTwoDto.getTaskDisplayName());
        logModelVTwo.setLevelDisplayName(logRequestVTwoDto.getLevelDisplayName());
        logVTwoRepo.save(logModelVTwo);
    }
    //Add logon log
    public void addLogonData(LogonDataRequestDto logonDataRequestDto) throws Exception {
        if(logonDataRequestDto.getUserName().isEmpty())
            throw new Exception("Some data missed");

        LogonDataModel logonDataModel = new LogonDataModel();
        logonDataModel.setUserName(logonDataRequestDto.getUserName());
        logonDataModel.setEventID(Long.parseLong(logonDataRequestDto.getEventID()));
        logonDataModel.setDateCreated(LocalDate.parse(logonDataRequestDto.getDateCreated()));
        logonDataModel.setTimeCreated(LocalTime.parse(logonDataRequestDto.getTimeCreated()));
        logonDataModel.setComputerName(logonDataModel.getComputerName());
        logonDataModel.setIpAddress(logonDataRequestDto.getIpAddress());
        try{
            logonDataRepo.save(logonDataModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    search by log id *****************************************************************
    @Override
    public List<LogResponseDto> searchByLogId(LogIdSearchRequestDto logIdSearchRequestDto) throws NotFound {
        List<LogResponseDto> logResponseDtoList = new ArrayList<>();
        try {
            Optional<List<LogModel>> logModelList = logRepo.findByLogId(logIdSearchRequestDto.getLogId());
            if (logModelList.isPresent()) {
                for (LogModel logModelResponse : logModelList.get()) {
                    LogResponseDto logResponseDto = getLogResponseDto(logModelResponse);
                    logResponseDtoList.add(logResponseDto);
                }
            }
            return logResponseDtoList;
        } catch (Exception e) {
            throw new NotFound("No such id found!!");

        }
    }

    // set the response dto of log for search by log id *****************************************************************
    private static LogResponseDto getLogResponseDto(LogModel logModelResponse) {
        LogResponseDto logResponseDto = new LogResponseDto();
        logResponseDto.setUserName(logModelResponse.getUsername());
        logResponseDto.setDeviceName(logModelResponse.getDevice_name());
        logResponseDto.setIpAddress(logModelResponse.getIPAddress());
        logResponseDto.setEventId(logModelResponse.getLog_id().toString());
        logResponseDto.setEventDate(logModelResponse.getLog_date().toString());
        logResponseDto.setEventTime(logModelResponse.getLog_time().toString());
        return logResponseDto;
    }

    // convert log model to log response dto *****************************************************************
    private LogResponseDto convertToDto(LogModel logModel) {
        LogResponseDto logResponseDto = new LogResponseDto();
        logResponseDto.setId(logModel.getId());
        logResponseDto.setEventId(logModel.getLog_id().toString());
        logResponseDto.setUserName(logModel.getUsername());
        logResponseDto.setDeviceName(logModel.getDevice_name());
        logResponseDto.setEventDate(logModel.getLog_date().toString());
        logResponseDto.setEventTime(logModel.getLog_time().toString());
        logResponseDto.setIpAddress(logModel.getIPAddress());
        return logResponseDto;
    }
}
