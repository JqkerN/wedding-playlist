package com.example.wedding.vote.controller;

import com.example.wedding.vote.dto.VoteRequest;
import com.example.wedding.vote.dto.NameTitleVote;
import com.example.wedding.vote.dto.VoteCount;
import com.example.wedding.vote.service.PlaylistVoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags={"Playlist Vote Controller"})
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PlaylistVoteController {
    private final PlaylistVoteService playlistVoteService;

    @ApiOperation(value = "Get all votes on the songs in the playlist.")
    @GetMapping("/playlist/votes")
    public List<NameTitleVote> getAllVotes(){
        return playlistVoteService.getAllVotes();
    }

    @ApiOperation(value = "Get all counts of the votes in the playlist.")
    @GetMapping("/playlist/votes/count")
    public List<VoteCount> getAllCountPerTrack(){
        return playlistVoteService.getVotesPerTrackIds();
    }

    @ApiOperation(value = "Add a vote to a song in the playlist.")
    @PostMapping("/playlist/votes")
    public void addVote(@Valid @RequestBody VoteRequest voteRequest){
        playlistVoteService.addVote(voteRequest);
    }

    @ApiOperation(value = "Remove a vote to a song in the playlist.")
    @DeleteMapping("/playlist/votes/{id}")
    public void removeVote(@PathVariable Integer id){
        playlistVoteService.removeVote(id);
    }

    @ApiOperation(value = "Get all votes for a specified song.")
    @GetMapping("/playlist/{trackId}/votes")
    public List<NameTitleVote> getTrackVotes(@PathVariable Integer trackId){
        return playlistVoteService.getAllVotes(trackId);
    }
}
