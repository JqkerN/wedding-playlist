package com.example.weedingplaylist.guest.repository;

import com.example.weedingplaylist.guest.model.Guest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j

public class GuestRepository implements IGuestRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int save(Guest guest) {
        String sql = """
                INSERT INTO guests(fullName) VALUE(?);
                """;
        return jdbcTemplate.update(sql, guest.getFullName());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = """
                DELETE FROM guests WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Guest> findAll() {
        String sql = """
                SELECT * FROM guests;
                """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> Guest.builder()
                        .id(rs.getInt("id"))
                        .fullName(rs.getString("fullName"))
                        .build()
        );
    }

    @Override
    public Guest findById(Integer id) {
        String sql = """
                SELECT * FROM guests WHERE id = ?;
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> Guest.builder()
                            .id(rs.getInt("id"))
                            .fullName(rs.getString("fullName"))
                            .build(),
                    id
            );
        } catch (RuntimeException e) {
            log.debug("Could not find guest with id {}", id, e);
        }
        return null;
    }

    @Override
    public Guest findByFullName(String fullName) {
        String sql = """
                SELECT * FROM guests WHERE fullName = ?;
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> Guest.builder()
                            .id(rs.getInt("id"))
                            .fullName(rs.getString("fullName"))
                            .build(),
                    fullName
            );
        } catch (RuntimeException e) {
            log.debug("Could not find guest with id {}", fullName, e);
        }
        return null;
    }
}
