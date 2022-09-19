package com.example.wedding.vote.repository;

import com.example.wedding.vote.model.Vote;
import com.example.wedding.vote.dto.NameTitleVote;
import com.example.wedding.vote.dto.VoteCount;

import java.util.List;

public interface IVoteRepository {
    List<NameTitleVote> findAll();

    List<NameTitleVote> findVote(Vote vote);

    List<NameTitleVote> findAllByTrackId(Integer trackId);

    List<NameTitleVote> findAllByGuestId(Integer guestId);

    List<VoteCount> countGuestIdPerTrackId();

    int save(Vote vote);

    int deleteById(Integer id);
}
