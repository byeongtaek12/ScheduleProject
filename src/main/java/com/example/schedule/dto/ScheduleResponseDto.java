package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleResponseDto {
    private Long id;
    private String name;
    private String password;
    private String todo;
    private LocalDateTime creationdate;
    private LocalDateTime modificationdate;

}
