package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getName(),dto.getPassword(),dto.getTodo(), LocalDateTime.now(),LocalDateTime.now());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        return new ScheduleResponseDto(scheduleRepository.findScheduleByIdOrElseThrow(id));
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String password, String name, String todo) {

        if (name==null || todo==null || password==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name and todo and password is required values.");
        }

        if (password.equals(scheduleRepository.findScheduleByIdOrElseThrow(id).getPassword())) {
            int updatedRow = scheduleRepository.updateSchedule(id, password,name, todo);

            if (updatedRow==0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id="+id);
            }

            Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

            return new ScheduleResponseDto(schedule);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "password is different");
    }

    @Override
    public void deleteSchedule(Long id,String password) {
        if (password==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is required values.");
        }

        if (password.equals(scheduleRepository.findScheduleByIdOrElseThrow(id).getPassword())) {
            int deletedRow = scheduleRepository.deleteSchedule(id,password);

            if(deletedRow==0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id="+id);
            }throw new ResponseStatusException(HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "password is different");
    }
}
