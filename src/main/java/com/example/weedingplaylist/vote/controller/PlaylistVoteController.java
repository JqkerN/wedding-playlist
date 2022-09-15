package com.example.weedingplaylist.vote.controller;

import com.example.weedingplaylist.vote.dto.VoteRequest;
import com.example.weedingplaylist.vote.dto.NameTitleVote;
import com.example.weedingplaylist.vote.dto.VoteCount;
import com.example.weedingplaylist.vote.service.PlaylistVoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PlaylistVoteController {
    private final PlaylistVoteService playlistVoteService;

    @GetMapping("/playlist/votes")
    public List<NameTitleVote> getAllVotes(){
        return playlistVoteService.getAllVotes();
    }

    @GetMapping("/playlist/votes/count")
    public List<VoteCount> getAllCountPerTrack(){
        return playlistVoteService.getVotesPerTrackIds();
    }

    @PostMapping("/playlist/votes")
    public void addVote(@Valid @RequestBody VoteRequest voteRequest){
        playlistVoteService.addVote(voteRequest);
    }

    @DeleteMapping("/playlist/votes/{id}")
    public void removeVote(@PathVariable Integer id){
        playlistVoteService.removeVote(id);
    }

    @GetMapping("/playlist/{trackId}/votes")
    public List<NameTitleVote> getTrackVotes(@PathVariable Integer trackId){
        return playlistVoteService.getAllVotes(trackId);
    }
}
