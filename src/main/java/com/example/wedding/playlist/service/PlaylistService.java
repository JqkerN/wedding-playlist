package com.example.wedding.playlist.service;

import com.example.wedding.exception.SongAlreadyExistException;
import com.example.wedding.playlist.model.*;
import com.example.wedding.playlist.repository.IPlaylistRepository;
import com.example.wedding.playlist.repository.IVoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PlaylistService {
    private final IPlaylistRepository playlistRepository;
    private final IVoteRepository voteRepository;

    public List<Song> getAllSongs() {
        return playlistRepository.findAll().stream()
                .map(songDto -> {
                    Map<Integer, Integer> votes = voteRepository.countVotes();
                    return Song.builder()
                            .id(songDto.getId())
                            .title(songDto.getTitle())
                            .artist(songDto.getArtist())
                            .albumCover(songDto.getAlbumCover())
                            .votes(votes.get(songDto.getId()))
                            .build();
                })
                .toList();
    }

    public List<Song> getAllSongs(Integer guestId) {
        return voteRepository.findVotesByGuestId(guestId).stream()
                .map(voteDto -> Song.builder()
                        .id(voteDto.getSongId())
                        .title(voteDto.getTitle())
                        .artist(voteDto.getArtist())
                        .albumCover(voteDto.getAlbumCover())
                        .votes(voteDto.getCount())
                        .build())
                .toList();
    }

    public boolean addSong(SongRegisterRequest songRegisterRequest) {
        SongDto songDto = SongDto.builder()
                .title(songRegisterRequest.getTitle())
                .artist(songRegisterRequest.getArtist())
                .albumCover(songRegisterRequest.getAlbumCover())
                .build();

        if (playlistRepository.findByTitle(songDto.getTitle()) != null) {
            throw new SongAlreadyExistException("Song with title[" + songDto.getTitle() + "] already exists.");
        }

        int rowsAffected = playlistRepository.save(songDto);

        if (rowsAffected > 0) {
            SongDto song = playlistRepository.findByTitle(songDto.getTitle());
            Vote vote = Vote.builder()
                    .guestId(songRegisterRequest.getGuestId())
                    .songId(song.getId())
                    .build();
            voteRepository.save(vote);
        }

        return rowsAffected > 0;
    }

    public boolean removeTrack(Integer id) {
        // TODO: remove all votes as well
        int rowsAffected = playlistRepository.deleteById(id);
        return rowsAffected > 0;
    }

    public Song getSong(Integer songId) {
        VoteDto voteDto = voteRepository.findVotesBySongId(songId);

        return Song.builder()
                .id(voteDto.getSongId())
                .title(voteDto.getTitle())
                .artist(voteDto.getArtist())
                .albumCover(voteDto.getAlbumCover())
                .build();
    }

    public void upVote(Integer songId, VoteRequest voteRequest) {
        Vote vote = Vote.builder()
                .songId(songId)
                .guestId(voteRequest.getGuestId())
                .build();

        voteRepository.save(vote);
    }

    public void downVote(Integer songId, Integer guestId) {
        // TODO remove if votes <= 0
        voteRepository.delete(songId, guestId);
    }
}
