package com.example.weedingplaylist.vote.service;

import com.example.weedingplaylist.guest.model.Guest;
import com.example.weedingplaylist.guest.repository.IGuestRepository;
import com.example.weedingplaylist.exception.TrackNotFoundException;
import com.example.weedingplaylist.playlist.model.Track;
import com.example.weedingplaylist.playlist.repository.IPlaylistRepository;
import com.example.weedingplaylist.vote.model.IdVote;
import com.example.weedingplaylist.vote.dto.VoteRequest;
import com.example.weedingplaylist.exception.GustNotFoundException;
import com.example.weedingplaylist.vote.model.NameTitleVote;
import com.example.weedingplaylist.vote.model.VoteCount;
import com.example.weedingplaylist.vote.repository.IVoteRepository;
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
            throw new GustNotFoundException("Guest with the full name["+voteRequest.getFullName()+"] does not exist.");
        }

        Track track = playlistRepository.findByTitle(voteRequest.getTitle());
        if (track == null) {
            throw new TrackNotFoundException("Track with the title["+voteRequest.getTitle()+"] does not exist.");
        }

        IdVote idVote = IdVote.builder()
                .guestId(guest.getId())
                .trackId(track.getId())
                .build();

        voteRepository.save(idVote);
    }

    public void removeVote(Integer id) {
        voteRepository.deleteById(id);
    }
}
