package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // repository) 일정 저장 메서드
    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", schedule.getName());
        parameters.put("password", schedule.getPassword());
        parameters.put("todo", schedule.getTodo());
        parameters.put("creationdate", schedule.getCreationdate());
        parameters.put("modificationdate", schedule.getModificationdate());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new ScheduleResponseDto(key.longValue(), schedule.getName(),schedule.getTodo(),
                schedule.getCreationdate(),schedule.getModificationdate());
    }

    // repository) 일정 목록 조회 메서드
    @Override
    public List<ScheduleResponseDto> findAllSchedules(String name, String modificationdate) {
        if (name==null && modificationdate==null) {
            return jdbcTemplate.query("select * from schedule order by modificationdate desc", scheduleRowMapper());
        }else if (name== null) {
            return jdbcTemplate.query("select * from schedule where modificationdate like ? order by modificationdate desc",
                    scheduleRowMapper(),modificationdate+"%");
        }else if (modificationdate== null) {
            return jdbcTemplate.query("select * from schedule where name=? order by modificationdate desc",
                    scheduleRowMapper(),name);
        }else{
            return jdbcTemplate.query("select * from schedule where name=? and modificationdate like ?" +
                    "order by modificationdate desc", scheduleRowMapper(),name,modificationdate+"%");
        }
    }

    // repository) 일정 단건 조회 메서드
    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperv2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = "+id));
    }

    // 각 행을 ScheduleResponseDto 타입으로 매핑 후 리스트로 반환해주는 메서드
    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(rs.getLong("id"), rs.getString("name"),
                        rs.getString("todo"),
                        rs.getTimestamp("creationdate").toLocalDateTime(),
                        rs.getTimestamp("modificationdate").toLocalDateTime());
            }
        };
    }

    // 각 행을 Schedule 타입으로 매핑 후 리스트로 반환해주는 메서드
    private RowMapper<Schedule> scheduleRowMapperv2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(rs.getLong("id"),rs.getString("name"),rs.getString("password"),
                        rs.getString("todo"),rs.getTimestamp("creationdate").toLocalDateTime(),
                        rs.getTimestamp("modificationdate").toLocalDateTime());
            }
        };
    }
}
