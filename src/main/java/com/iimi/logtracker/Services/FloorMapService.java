package com.iimi.logtracker.Services;

import com.iimi.logtracker.DTOs.FloorResponseDto;
import com.iimi.logtracker.Exception.NotFound;
import com.iimi.logtracker.Models.FloorMapModel;
import com.iimi.logtracker.Repo.FloorMapRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloorMapService implements FloorMapInterface{
    private final FloorMapRepo floorMapRepo;

    public FloorMapService(FloorMapRepo floorMapRepo) {
        this.floorMapRepo = floorMapRepo;
    }

    @Override
    public List<FloorResponseDto> getFloorMap() throws NotFound {
           List<FloorMapModel> floorMapModel=floorMapRepo.findAll();
           if(floorMapModel.isEmpty()){
               throw new NotFound("No data found");
        }

        List<FloorResponseDto> floorResponseDtos = new ArrayList<>();
           for(FloorMapModel responseModel:floorMapModel){
               FloorResponseDto floorResponseDto = new FloorResponseDto();
               floorResponseDto.setCpuId(responseModel.getCpuId());
               floorResponseDto.setDisplayId(responseModel.getDisplayId());
               floorResponseDto.setKeyboardId(responseModel.getKeyboardId());
               floorResponseDto.setMouseId(responseModel.getMouseId());
               floorResponseDtos.add(floorResponseDto);
           }
           return floorResponseDtos;
    }

    @Override
    public String addFloorMap() {
        return "";
    }
}
