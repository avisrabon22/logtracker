package com.iimi.logtracker.Controllers;


import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogResponseDto;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Services.LogInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/log")
public class LogController {
    private final LogInterface logInterface;

    public LogController(LogInterface logInterface) {
        this.logInterface = logInterface;
    }

    @GetMapping("/get-logs")
    public ResponseEntity<?> getLogs() throws NotFound {
      return ResponseEntity.ok().body(logInterface.getLogs());
    }

    @PostMapping("/add-log")
    public ResponseEntity<?> addLog(@RequestBody LogRequestDto logRequestDto) throws Exception {

          if(logRequestDto.getUserName()==null)
              throw new Exception("Data empty");
        logInterface.addLogs(logRequestDto);

        return ResponseEntity.ok().body("Log added");
    }
}
