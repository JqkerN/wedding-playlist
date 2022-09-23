package com.example.wedding.playlist.controller;

import com.example.wedding.playlist.model.Song;
import com.example.wedding.playlist.model.SongRegisterRequest;
import com.example.wedding.playlist.model.VoteRequest;
import com.example.wedding.playlist.service.PlaylistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = {"Playlist Controller"})
@RestController
@RequestMapping("/api/v1/playlist")
@AllArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @ApiOperation(value = "Get all songs from the playlist.")
    @GetMapping
    public ResponseEntity<List<Song>> allSongs(@RequestParam(value = "guestId", required = false) Integer guestId) {
        if (guestId != null) {
            return ResponseEntity.ok(playlistService.getAllSongs(guestId));
        }
        return ResponseEntity.ok(playlistService.getAllSongs());
    }

    @ApiOperation(value = "Get a song from the playlist.")
    @GetMapping("/{songId}")
    public ResponseEntity<Song> getSong(@NotNull @PathVariable Integer songId) {
        return ResponseEntity.ok(playlistService.getSong(songId));
    }

    @ApiOperation(value = "Add a song to the playlist.")
    @PostMapping
    public ResponseEntity<String> addSong(@Valid @RequestBody SongRegisterRequest songRegisterRequest) {
        boolean added = playlistService.addSong(songRegisterRequest);
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Song added.");
        } else {
            return ResponseEntity.ok("Song already exist.");
        }
    }

    @ApiOperation(value = "Remove a song from the playlist.")
    @DeleteMapping("/{songId}")
    public ResponseEntity<?> removeSong(@NotNull @PathVariable Integer songId) {
        boolean removed = playlistService.removeTrack(songId);
        if (removed) {
            return ResponseEntity.ok("Song removed.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Song not found.");
        }
    }

    @ApiOperation(value = "Add a song to the playlist.")
    @PostMapping("/{songId}/vote")
    public ResponseEntity<?> upVote(@NotNull @PathVariable Integer songId, @Valid @RequestBody VoteRequest voteRequest) {
        playlistService.upVote(songId, voteRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Remove a song from the playlist.")
    @DeleteMapping("/{songId}/vote/{guestId}")
    public ResponseEntity<?> downVote(@NotNull @PathVariable Integer songId, @NotNull @PathVariable Integer guestId) {
        playlistService.downVote(songId, guestId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
