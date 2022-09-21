package com.example.wedding.playlist.repository;

import com.example.wedding.playlist.model.SongDto;

import java.util.List;

public interface IPlaylistRepository {
    int save(SongDto songDto);
    int deleteById(Integer id);
    List<SongDto> findAll();

    SongDto findByTitle(String title);
    SongDto findById(Integer id);

}
