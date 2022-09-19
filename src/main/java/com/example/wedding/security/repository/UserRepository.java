package com.example.wedding.security.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public int saveUser(String username, String password, String role) {
        String sql = """
                INSERT INTO users(username, password, role) VALUE(?,?,?);
                """;
        return jdbcTemplate.update(sql, username, password, role);
    }

    public int updateUser(String username, String password, String role) {
        String sql = """
                UPDATE users
                SET username = ?, password = ?, role = ?
                WHERE username = ?;
                """;
        return jdbcTemplate.update(sql, username, password, role, username);
    }

    public int deleteUser(String username) {
        String sql = """
                DELETE FROM users
                WHERE username = ?;
                """;
        return jdbcTemplate.update(sql, username);
    }

    public int deleteUserById(Integer id) {
        String sql = """
                DELETE FROM users
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    public List<UserDetails> findAll() {
        String sql = """
                SELECT * FROM users;
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> User.builder()
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .roles(rs.getString("role"))
                        .build()
        );
    }

    public UserDetails findUser(String username) {
        String sql = """
                SELECT * FROM users
                WHERE username = ?;
                """;
        try {
            UserDetails userDetails = jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> User.builder()
                            .username(rs.getString("username"))
                            .password(rs.getString("password"))
                            .roles(rs.getString("role"))
                            .build(),
                    username
            );
            return userDetails;
        } catch (RuntimeException e) {
            log.debug("Could not find user by the name {}", username, e);
        }
        return null;
    }
}
