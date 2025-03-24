package com.example.schedule.dto;

import java.time.LocalDateTime;

public class ScheduleResponseDto {
    private Long id;
    private String name;
    private String password;
    private String todo;
    private LocalDateTime creationdate;
    private LocalDateTime modificationdate;

    public ScheduleResponseDto(Long id, String name, String password, String todo) {
        this.id=id;
        this.name=name;
        this.password=password;
        this.todo=todo;
    }
}
