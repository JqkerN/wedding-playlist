package com.example.wedding.vote.service;

import com.example.wedding.guest.model.Guest;
import com.example.wedding.guest.repository.IGuestRepository;
import com.example.wedding.exception.TrackNotFoundException;
import com.example.wedding.playlist.model.Track;
import com.example.wedding.playlist.repository.IPlaylistRepository;
import com.example.wedding.vote.model.Vote;
import com.example.wedding.vote.dto.VoteRequest;
import com.example.wedding.exception.GuestNotFoundException;
import com.example.wedding.vote.dto.NameTitleVote;
import com.example.wedding.vote.dto.VoteCount;
import com.example.wedding.vote.repository.IVoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistVoteService {
    private final IVoteRepository voteRepository;
    private final IGuestRepository guestRepository;
    private final IPlaylistRepository playlistRepository;

    public List<NameTitleVote> getAllVotes() {
        return voteRepository.findAll();
    }

    public List<NameTitleVote> getAllVotes(Integer trackId) {
        return voteRepository.findAllByTrackId(trackId);
    }

    public List<VoteCount> getVotesPerTrackIds(){
        return voteRepository.countGuestIdPerTrackId();
    }

    public void addVote(VoteRequest voteRequest) {
        Guest guest = guestRepository.findByFullName(voteRequest.getFullName());
        if (guest == null) {
            throw new GuestNotFoundException("Guest with the full name["+voteRequest.getFullName()+"] does not exist.");
        }

        Track track = playlistRepository.findByTitle(voteRequest.getTitle());
        if (track == null) {
            throw new TrackNotFoundException("Track with the title["+voteRequest.getTitle()+"] does not exist.");
        }

        Vote vote = Vote.builder()
                .guestId(guest.getId())
                .trackId(track.getId())
                .build();

        voteRepository.save(vote);
    }

    public void removeVote(Integer id) {
        voteRepository.deleteById(id);
    }
}
