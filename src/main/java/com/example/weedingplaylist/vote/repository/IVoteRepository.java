package com.example.weedingplaylist.vote.repository;

import com.example.weedingplaylist.vote.model.Vote;
import com.example.weedingplaylist.vote.dto.NameTitleVote;
import com.example.weedingplaylist.vote.dto.VoteCount;

import java.util.List;

public interface IVoteRepository {
    List<NameTitleVote> findAll();

    List<NameTitleVote> findAllByTrackId(Integer trackId);

    List<VoteCount> countGuestIdPerTrackId();

    int save(Vote vote);

    int deleteById(Integer id);
}
