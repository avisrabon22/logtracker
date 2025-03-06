package com.iimi.logtracker.Services;


import com.iimi.logtracker.DTOs.FloorResponseDto;
import com.iimi.logtracker.Exception.NotFound;

import java.util.List;

public interface FloorMapInterface {
    List<FloorResponseDto> getFloorMap() throws NotFound;
    String addFloorMap();
}
