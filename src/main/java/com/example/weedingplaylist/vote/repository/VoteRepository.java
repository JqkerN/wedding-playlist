package com.example.weedingplaylist.vote.repository;

import com.example.weedingplaylist.vote.model.IdVote;
import com.example.weedingplaylist.vote.model.NameTitleVote;
import com.example.weedingplaylist.vote.model.VoteCount;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class VoteRepository implements IVoteRepository{
    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<NameTitleVote> findAll() {
        String sql = """
                SELECT votes.id, guests.fullName, tracks.title
                FROM votes
                INNER JOIN guests ON guestId = guests.id
                INNER JOIN tracks ON trackId = tracks.id;
                """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> NameTitleVote.builder()
                        .id(rs.getInt("id"))
                        .fullName(rs.getString("fullName"))
                        .title(rs.getString("title"))
                        .build()
        );
    }

    @Override
    public List<NameTitleVote> findAllByTrackId(Integer trackId) {
        String sql = """
                SELECT votes.id, guests.fullName, tracks.title
                FROM votes
                INNER JOIN guests ON guestId = guests.id
                INNER JOIN tracks ON trackId = tracks.id
                WHERE tracks.id = ?;
                """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> NameTitleVote.builder()
                        .id(rs.getInt("id"))
                        .fullName(rs.getString("fullName"))
                        .title(rs.getString("title"))
                        .build(),
                trackId
        );
    }

    @Override
    public List<VoteCount> countGuestIdPerTrackId() {
        String sql = """
                SELECT tracks.title, COUNT(*) AS count
                FROM votes
                INNER JOIN tracks ON trackId = tracks.id
                GROUP BY trackId;
                """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> VoteCount.builder()
                        .title(rs.getString("title"))
                        .count(rs.getInt("count"))
                        .build()
        );
    }

    @Override
    public int save(IdVote idVote) {
        String sql = """
                INSERT INTO tracks(trackId, guestId) VALUE(?, ?);
                """;
        return jdbcTemplate.update(sql, idVote.getTrackId(), idVote.getGuestId());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = """
                DELETE FROM tracks WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }
}
