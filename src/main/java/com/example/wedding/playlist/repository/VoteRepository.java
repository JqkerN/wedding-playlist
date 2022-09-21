package com.example.wedding.playlist.repository;

import com.example.wedding.playlist.model.Vote;
import com.example.wedding.playlist.model.VoteDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
@Slf4j
public class VoteRepository implements IVoteRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public VoteDto findVotesBySongId(Integer songId) {
        String sql = """
                SELECT songs.title AS title, songs.artist AS artist, songs.albumCover AS albumCover, songs.id AS songId, COUNT(*) AS count
                FROM votes
                INNER JOIN guests ON guestId = guests.id
                INNER JOIN songs ON songId = songs.id
                WHERE songs.id = ?
                GROUP BY songs.id;
                """;
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> VoteDto.builder()
                        .songId(rs.getInt("songId"))
                        .title(rs.getString("title"))
                        .artist(rs.getString("artist"))
                        .albumCover(rs.getString("albumCover"))
                        .count(rs.getInt("count"))
                        .build(),
                songId
        );
    }

    @Override
    public List<VoteDto> findVotesByGuestId(Integer guestId) {
        String sql = """
                SELECT songs.title AS title, songs.artist AS artist, songs.albumCover AS albumCover, songs.id AS songId, COUNT(*) AS count
                FROM votes
                INNER JOIN guests ON guestId = guests.id
                INNER JOIN songs ON songId = songs.id
                WHERE guests.id = ?
                GROUP BY songs.id;
                """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> VoteDto.builder()
                        .songId(rs.getInt("songId"))
                        .title(rs.getString("title"))
                        .artist(rs.getString("artist"))
                        .albumCover(rs.getString("albumCover"))
                        .count(rs.getInt("count"))
                        .build(),
                guestId
        );
    }

    @Override
    public Map<Integer, Integer> countVotes() {
        String sql = """
                SELECT songs.title AS title, songs.artist AS artist, songs.albumCover AS albumCover, songs.id AS songId, COUNT(*) AS count
                FROM votes
                INNER JOIN guests ON guestId = guests.id
                INNER JOIN songs ON songId = songs.id
                GROUP BY songs.id;
                """;
        Map<Integer, Integer> result = new HashMap<>();
        jdbcTemplate.query(sql, (rs, rowNum) -> result.put(rs.getInt("songId"), rs.getInt("count")));
        return result;
    }

    @Override
    public int save(Vote vote) {
        String sql = """
                INSERT INTO votes(songId, guestId) VALUE(?, ?);
                """;
        return jdbcTemplate.update(sql, vote.getSongId(), vote.getGuestId());
    }

    @Override
    public int delete(Integer guestId, Integer songId) {
        String sql = """
                DELETE FROM votes
                WHERE guestId = ? AND songId = ?;
                """;
        return jdbcTemplate.update(sql, guestId, songId);
    }


//    @Override
//    public List<NameTitleVote> findAll() {
//        String sql = """
//                SELECT votes.id, guests.fullName, tracks.title
//                FROM votes
//                INNER JOIN guests ON guestId = guests.id
//                INNER JOIN tracks ON trackId = tracks.id;
//                """;
//        return jdbcTemplate.query(sql,
//                (rs, rowNum) -> NameTitleVote.builder()
//                        .id(rs.getInt("id"))
//                        .fullName(rs.getString("fullName"))
//                        .title(rs.getString("title"))
//                        .build()
//        );
//    }
//
//    @Override
//    public List<NameTitleVote> findVote(Vote vote) {
//        String sql = """
//                SELECT votes.id, guests.fullName, tracks.title
//                FROM votes
//                INNER JOIN guests ON guestId = guests.id
//                INNER JOIN tracks ON trackId = tracks.id
//                WHERE guestId = ? AND trackId = ?;
//                """;
//        return jdbcTemplate.query(sql,
//                (rs, rowNum) -> NameTitleVote.builder()
//                        .id(rs.getInt("id"))
//                        .fullName(rs.getString("fullName"))
//                        .title(rs.getString("title"))
//                        .build(),
//                vote.getGuestId(),
//                vote.getTrackId()
//        );
//    }
//
//    @Override
//    public List<NameTitleVote> findAllByTrackId(Integer trackId) {
//        String sql = """
//                SELECT votes.id, guests.fullName, tracks.title
//                FROM votes
//                INNER JOIN guests ON guestId = guests.id
//                INNER JOIN tracks ON trackId = tracks.id
//                WHERE tracks.id = ?;
//                """;
//        return jdbcTemplate.query(sql,
//                (rs, rowNum) -> NameTitleVote.builder()
//                        .id(rs.getInt("id"))
//                        .fullName(rs.getString("fullName"))
//                        .title(rs.getString("title"))
//                        .build(),
//                trackId
//        );
//    }
//
//    @Override
//    public List<NameTitleVote> findAllByGuestId(Integer guestId) {
//        String sql = """
//                SELECT votes.id, guests.fullName, tracks.title
//                FROM votes
//                INNER JOIN guests ON guestId = guests.id
//                INNER JOIN tracks ON trackId = tracks.id
//                WHERE guests.id = ?;
//                """;
//        return jdbcTemplate.query(sql,
//                (rs, rowNum) -> NameTitleVote.builder()
//                        .id(rs.getInt("id"))
//                        .fullName(rs.getString("fullName"))
//                        .title(rs.getString("title"))
//                        .build(),
//                guestId
//        );
//    }
//
//    @Override
//    public List<VoteCount> countGuestIdPerTrackId() {
//        String sql = """
//                SELECT tracks.title, COUNT(*) AS count
//                FROM votes
//                INNER JOIN tracks ON trackId = tracks.id
//                GROUP BY trackId;
//                """;
//        return jdbcTemplate.query(sql,
//                (rs, rowNum) -> VoteCount.builder()
//                        .title(rs.getString("title"))
//                        .count(rs.getInt("count"))
//                        .build()
//        );
//    }
//
//    @Override
//    public int save(Vote vote) {
//        String sql = """
//                INSERT INTO votes(trackId, guestId) VALUE(?, ?);
//                """;
//        return jdbcTemplate.update(sql, vote.getTrackId(), vote.getGuestId());
//    }
//
//    @Override
//    public int deleteById(Integer id) {
//        String sql = """
//                DELETE FROM tracks WHERE id = ?;
//                """;
//        return jdbcTemplate.update(sql, id);
//    }
}
