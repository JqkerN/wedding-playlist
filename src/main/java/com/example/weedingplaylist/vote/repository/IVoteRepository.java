package com.example.weedingplaylist.vote.repository;

import com.example.weedingplaylist.vote.model.IdVote;
import com.example.weedingplaylist.vote.model.NameTitleVote;
import com.example.weedingplaylist.vote.model.VoteCount;

import java.util.List;

public interface IVoteRepository {
    List<NameTitleVote> findAll();

    List<NameTitleVote> findAllByTrackId(Integer trackId);

    List<VoteCount> countGuestIdPerTrackId();

    int save(IdVote idVote);

    int deleteById(Integer id);
}
