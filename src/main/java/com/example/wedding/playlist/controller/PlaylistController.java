package com.example.wedding.playlist.controller;

import com.example.wedding.playlist.service.PlaylistService;
import com.example.wedding.playlist.model.Track;
import com.example.wedding.playlist.dto.TrackRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/playlist")
@AllArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public List<Track> allTracks() {
        return playlistService.getAllTracks();
    }

    @PostMapping
    public void addTrack(@Valid @RequestBody TrackRegisterRequest trackRegisterRequest) {
        playlistService.addTrack(trackRegisterRequest);
    }

    @DeleteMapping("/{id}")
    public void removeTrack(@PathVariable() Integer id) {
        playlistService.removeTrack(id);
    }
}
