package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String name;
    private String password;
    private String todo;
    private LocalDateTime creationdate;
    private LocalDateTime modificationdate;

}
