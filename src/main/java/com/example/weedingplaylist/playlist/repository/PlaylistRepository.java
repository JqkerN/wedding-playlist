package com.example.weedingplaylist.playlist.repository;

import com.example.weedingplaylist.playlist.model.Track;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class PlaylistRepository implements IPlaylistRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(Id) FROM tracks;
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int save(Track track) {
        String sql = """
                INSERT INTO tracks(title) VALUE(?);
                """;
        return jdbcTemplate.update(sql, track.getTitle());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = """
                DELETE FROM tracks WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Track> findAll() {
        String sql = """
                SELECT * FROM tracks;
                """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> Track.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("title"))
                        .build()
        );
    }

    @Override
    public Track findByTitle(String title) {
        String sql = """
                SELECT * FROM tracks WHERE title = ?;
                """;
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> Track.builder()
                            .id(rs.getInt("id"))
                            .title(rs.getString("title"))
                            .build(),
                    title
            );
        } catch (RuntimeException e) {
            log.debug("Could not find track with title {}", title, e);
        }
        return null;
    }

    @Override
    public Track findById(Integer id) {
        String sql = """
                SELECT * FROM tracks WHERE id = ?;
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> Track.builder()
                            .id(rs.getInt("id"))
                            .title(rs.getString("title"))
                            .build(),
                    id
            );
        } catch (RuntimeException e) {
            log.debug("Could not find track with id {}", id, e);
        }
        return null;
    }
}
