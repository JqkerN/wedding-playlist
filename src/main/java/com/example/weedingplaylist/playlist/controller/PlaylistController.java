package com.example.weedingplaylist.playlist.controller;

import com.example.weedingplaylist.playlist.service.PlaylistService;
import com.example.weedingplaylist.playlist.model.Track;
import com.example.weedingplaylist.playlist.dto.TrackRegisterRequest;
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
