package com.example.weedingplaylist.playlist.repository;

import com.example.weedingplaylist.playlist.model.Track;

import java.util.List;

public interface IPlaylistRepository {
    int count();
    int save(Track track);
    int deleteById(Integer id);
    List<Track> findAll();
    Track findByTitle(String title);
    Track findById(Integer id);

}
