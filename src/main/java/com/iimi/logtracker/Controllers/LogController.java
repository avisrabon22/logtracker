package com.iimi.logtracker.Controllers;

import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Services.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/log")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/get-logs")
    public ResponseEntity<?> getLog() {
        // get log
        List<LogResponseDto> logs =logService.getLogs();
        return ResponseEntity.ok().body(logs);
    }

    @PostMapping("/add-logs")
    public void addLogs(@RequestBody LogRequestDto logRequestDto){
         System.out.println(logRequestDto.getLog_time());
        logService.addLogs(logRequestDto);
    }

}
