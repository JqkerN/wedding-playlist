package com.example.weedingplaylist.playlist.service;

import com.example.weedingplaylist.exception.TrackAlreadyExistException;
import com.example.weedingplaylist.playlist.model.Track;
import com.example.weedingplaylist.playlist.dto.TrackRegisterRequest;
import com.example.weedingplaylist.playlist.repository.IPlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistService {
    private final IPlaylistRepository playlistRepository;

    public List<Track> getAllTracks() {
        return playlistRepository.findAll();
    }

    public void addTrack(TrackRegisterRequest trackRegisterRequest) {
        Track track = Track.builder()
                .title(trackRegisterRequest.getTitle())
                .build();

        if (playlistRepository.findByTitle(track.getTitle()) != null) {
            throw new TrackAlreadyExistException("Track with title["+track.getTitle()+"] already exists.");
        }

        playlistRepository.save(track);
    }

    public void removeTrack(Integer id) {
        playlistRepository.deleteById(id);
    }
}
