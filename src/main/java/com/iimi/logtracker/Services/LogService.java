package com.iimi.logtracker.Services;


import com.iimi.logtracker.DTOs.LogIdSearchRequestDto;
import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Models.LogModel;
import com.iimi.logtracker.Repo.LogRepo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        if(logRequestDto.getUsername()==null|| logRequestDto.getEventID()==null)
            throw new RuntimeException("Some value not getting");
        LogModel logModel = new LogModel();
        logModel.setUsername(logRequestDto.getUsername());
        logModel.setDevice_name(logRequestDto.getDeviceName());
        logModel.setLog_id(Long.parseLong(logRequestDto.getEventID()));
//            Set date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate logDate = LocalDate.parse(logRequestDto.getEventDate(), dateFormatter);
        logModel.setLog_date(logDate);
//            Set time


        logModel.setIPAddress(logRequestDto.getIPAddress());

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
        logResponseDto.setId(logModelResponse.getLog_id());
        logResponseDto.setUsername(logModelResponse.getUsername());
        logResponseDto.setDevice_name(logModelResponse.getDevice_name());
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
        logResponseDto.setLog_id(logModel.getLog_id());
        logResponseDto.setLog_date(logModel.getLog_date());
        logResponseDto.setLog_time(logModel.getLog_time());
        return logResponseDto;
    }
}
