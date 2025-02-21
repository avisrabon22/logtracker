package com.iimi.logtracker.Services;
import com.iimi.logtracker.DTOs.LogIdSearchRequestDto;
import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Models.LogModel;
import com.iimi.logtracker.Repo.LogRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LogService implements LogInterface {
    private final LogRepo logRepo;


    public LogService(LogRepo logRepo) {
        this.logRepo = logRepo;
    }
//    get logs *****************************************************************
    @Override
    public List<LogResponseDto> getLogs() throws NotFound {
        List<LogModel> logResponseDtos =new ArrayList<>();
        try {
            logResponseDtos = logRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error in fetching logs");
        }

        if (logResponseDtos.isEmpty())
            throw new NotFound("No data found!");
        return logResponseDtos.stream().map(this::convertToDto).toList();


    }
//    add logs *****************************************************************
    @Override
    public void addLogs(LogRequestDto logRequestDto) {
        if(logRequestDto.getUserName()==null)
            throw new RuntimeException("Some value not getting");

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

        try{
            logRepo.save(logModel);
        }catch (Exception e)
        {
            throw new RuntimeException("Unable to add logs");
        }
    }

//    search by log id *****************************************************************
    @Override
    public List<LogResponseDto> searchByLogId(LogIdSearchRequestDto logIdSearchRequestDto) throws NotFound {
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
        }catch (Exception e){
            throw  new NotFound("No such id found!!");

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
