package com.example.wedding.playlist.repository;

import com.example.wedding.playlist.model.VoteDto;
import com.example.wedding.playlist.model.Vote;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IVoteRepository {

    VoteDto findVotesBySongId(Integer songId);

    List<VoteDto> findVotesByGuestId(Integer guestId);

    Map<Integer, Integer> countVotes();

    int save(Vote vote);

    int delete(Integer guestId, Integer songId);
}
