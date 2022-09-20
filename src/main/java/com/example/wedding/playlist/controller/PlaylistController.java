package com.example.wedding.playlist.controller;

import com.example.wedding.playlist.service.PlaylistService;
import com.example.wedding.playlist.model.Track;
import com.example.wedding.playlist.dto.TrackRegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags={"Playlist Controller"})
@RestController
@RequestMapping("/api/v1/playlist")
@AllArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @ApiOperation(value = "Get all songs from the playlist.")
    @GetMapping
    public List<Track> allTracks() {
        return playlistService.getAllTracks();
    }

    @ApiOperation(value = "Add a song to the playlist.")
    @PostMapping
    public void addTrack(@Valid @RequestBody TrackRegisterRequest trackRegisterRequest) {
        playlistService.addTrack(trackRegisterRequest);
    }

    @ApiOperation(value = "Remove a song from the playlist.")
    @DeleteMapping("/{id}")
    public void removeTrack(@PathVariable() Integer id) {
        playlistService.removeTrack(id);
    }
}
