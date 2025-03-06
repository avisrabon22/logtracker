package com.iimi.logtracker.Controllers;

import com.iimi.logtracker.DTOs.FloorMapRequestDto;
import com.iimi.logtracker.DTOs.FloorResponseDto;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Services.FloorMapInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/floor-map")
public class FloorMapController {
    private final FloorMapInterface floorMapInterface;

    public FloorMapController(FloorMapInterface floorMapInterface) {
        this.floorMapInterface = floorMapInterface;
    }

    @GetMapping("/get-floor-maps")
    public ResponseEntity<?> getFloorMap() throws NotFound {
        return ResponseEntity.ok().body(floorMapInterface.getFloorMap());
    }

    @PostMapping("/add-floor")
    public ResponseEntity<?> addFloorMap(FloorMapRequestDto floorMapRequestDto){
        return ResponseEntity.ok().body("");
    }
}
