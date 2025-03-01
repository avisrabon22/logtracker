package com.iimi.logtracker.Controllers;


import com.iimi.logtracker.DTOs.LogRequestDto;
import com.iimi.logtracker.DTOs.LogRequestVTwoDto;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.DTOs.LogonDataRequestDto;
import com.iimi.logtracker.Services.LogInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/log")
public class LogController {
    private final LogInterface logInterface;


    public LogController(LogInterface logInterface) {
        this.logInterface = logInterface;
    }

//    Get data for logs *******************************************************************************************************
    @GetMapping("/get-logs")
    public ResponseEntity<?> getLogs() throws NotFound {
      return ResponseEntity.ok().body(logInterface.getLogs());
    }
//    Get data for logs for version *******************************************************************************************
    @GetMapping("/get-logs-v-two")
    public ResponseEntity<?> getLogsVTwo(){
        return ResponseEntity.ok().body("");
    }
// get log on data ****************************************************
@GetMapping("/add-logon-logs")
public ResponseEntity<?> getLogOnData(@RequestBody LogonDataRequestDto logonDataRequestDto) throws NotFound {
        return ResponseEntity.ok().body(logInterface.getLogOn(logonDataRequestDto));
}

//    Add logs from system ****************************************************************************************************
    @PostMapping("/add-log")
    public ResponseEntity<?> addLog(@RequestBody LogRequestDto logRequestDto) throws Exception {

          if(logRequestDto.getUserName()==null)
              throw new Exception("Data empty");
        logInterface.addLogs(logRequestDto);

        return ResponseEntity.ok().body("Log added");
    }
//    add logs version two *****************************************************************************************
    @PostMapping("/add-logs-v-two")
    public ResponseEntity<?> addLogsVTwo(@RequestBody LogRequestVTwoDto logRequestVTwoDto) throws Exception {
        logInterface.addLogsVTwo(logRequestVTwoDto);
        return ResponseEntity.ok().body("Data added in store");
    }
//    add login user logs for Windows system in domain of company by powershell script ****************************************
    @PostMapping("/add-logon-logs")
    public ResponseEntity<?> addLogonData(@RequestBody LogonDataRequestDto logonDataRequestDto) throws Exception {
        System.out.println(logonDataRequestDto.getUserName());
        logInterface.addLogonData(logonDataRequestDto);
        return ResponseEntity.ok().body("Data added in store");
    }
}
