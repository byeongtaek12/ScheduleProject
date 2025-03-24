package com.example.schedule.entity;

import lombok.Getter;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long id;
    private String name;
    private String password;
    private String todo;
    private LocalDateTime creationdate;
    private LocalDateTime modificationdate;

    public Schedule(String name, String password, String todo) {
        this.name= name;
        this.password = password;
        this.todo = todo;
    }
}
