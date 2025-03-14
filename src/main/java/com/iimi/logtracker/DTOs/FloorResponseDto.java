package com.iimi.logtracker.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FloorResponseDto {
    private Long id;
    private String cpuId;
    private String displayId;
    private String keyboardId;
    private String mouseId;
}
