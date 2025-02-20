package com.iimi.logtracker.Controllers;

import com.iimi.logtracker.DTOs.LogIdSearchRequestDto;
import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Services.LogInterface;
import com.iimi.logtracker.Services.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/log")
public class LogController {
    private final LogInterface logInterface;

    public LogController(LogService logService, LogInterface logInterface) {
        this.logInterface = logInterface;
    }

    @GetMapping("/get-logs")
    public ResponseEntity<?> getLog() throws NotFound {
        // get log
        List<LogResponseDto> logs =logInterface.getLogs();
        return ResponseEntity.ok().body(logs);
    }

    @PostMapping("/add-logs")
    public void addLogs(@RequestBody LogRequestDto logRequestDto){
        System.out.println(logRequestDto.getUsername()
                +" "+logRequestDto.getDeviceName()
                +" "+logRequestDto.getIPAddress()
                +" "+logRequestDto.getEventID()
                +" "+logRequestDto.getEventTime()
        +" "+logRequestDto.getEventDate());
        logInterface.addLogs(logRequestDto);
    }

    public ResponseEntity<?> searchByLogId(@RequestBody LogIdSearchRequestDto logIdSearchRequestDto) throws NotFound {
        if(logIdSearchRequestDto.getLogId().describeConstable().isEmpty()){
            return ResponseEntity.badRequest().body("Log Id is required");
        }
        return ResponseEntity.ok().body(logInterface.searchByLogId(logIdSearchRequestDto));
    }
}
