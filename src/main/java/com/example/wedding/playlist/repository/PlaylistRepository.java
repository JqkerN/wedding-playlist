package com.example.wedding.playlist.repository;

import com.example.wedding.playlist.model.SongDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class PlaylistRepository implements IPlaylistRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int save(SongDto songDto) {
        String sql = """
                INSERT INTO songs(title, artist, albumCover) VALUE(?,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        return jdbcTemplate.update(sql, songDto.getTitle(), songDto.getArtist(), songDto.getAlbumCover());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = """
                DELETE FROM songs WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<SongDto> findAll() {
        String sql = """
                SELECT * FROM songs;
                """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> SongDto.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("title"))
                        .artist(rs.getString("artist"))
                        .albumCover(rs.getString("albumCover"))
                        .build()
        );
    }

    @Override
    public SongDto findByTitle(String title) {
        String sql = """
                SELECT * FROM songs WHERE title = ?;
                """;
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> SongDto.builder()
                            .id(rs.getInt("id"))
                            .title(rs.getString("title"))
                            .artist(rs.getString("artist"))
                            .albumCover(rs.getString("albumCover"))
                            .build(),
                    title
            );
        } catch (RuntimeException e) {
            log.debug("Could not find track with title {}", title, e);
        }
        return null;
    }

    @Override
    public SongDto findById(Integer id) {
        String sql = """
                SELECT * FROM songs WHERE id = ?;
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> SongDto.builder()
                            .id(rs.getInt("id"))
                            .title(rs.getString("title"))
                            .artist(rs.getString("artist"))
                            .albumCover(rs.getString("albumCover"))
                            .build(),
                    id
            );
        } catch (RuntimeException e) {
            log.debug("Could not find track with id {}", id, e);
        }
        return null;
    }
}
