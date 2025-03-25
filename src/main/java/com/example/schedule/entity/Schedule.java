package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Schedule {
    private Long id;
    private String name;
    private String password;
    private String todo;
    private LocalDateTime creationdate;
    private LocalDateTime modificationdate;

    public Schedule(String name, String password, String todo,
                    LocalDateTime creationdate,LocalDateTime modificationdate) {
        this.name= name;
        this.password = password;
        this.todo = todo;
        this.creationdate=creationdate;
        this.modificationdate=modificationdate;
    }
}
