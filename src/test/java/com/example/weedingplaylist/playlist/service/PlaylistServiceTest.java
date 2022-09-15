package com.example.weedingplaylist.playlist.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.weedingplaylist.exception.TrackAlreadyExistException;
import com.example.weedingplaylist.playlist.dto.TrackRegisterRequest;
import com.example.weedingplaylist.playlist.model.Track;
import com.example.weedingplaylist.playlist.repository.IPlaylistRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PlaylistService.class})
@ExtendWith(SpringExtension.class)
class PlaylistServiceTest {
    @MockBean
    private IPlaylistRepository iPlaylistRepository;

    @Autowired
    private PlaylistService playlistService;

    /**
     * Method under test: {@link PlaylistService#getAllTracks()}
     */
    @Test
    void testGetAllTracks() {
        ArrayList<Track> trackList = new ArrayList<>();
        when(iPlaylistRepository.findAll()).thenReturn(trackList);
        List<Track> actualAllTracks = playlistService.getAllTracks();
        assertSame(trackList, actualAllTracks);
        assertTrue(actualAllTracks.isEmpty());
        verify(iPlaylistRepository).findAll();
    }

    /**
     * Method under test: {@link PlaylistService#getAllTracks()}
     */
    @Test
    void testGetAllTracks2() {
        when(iPlaylistRepository.findAll()).thenThrow(new TrackAlreadyExistException("An error occurred"));
        assertThrows(TrackAlreadyExistException.class, () -> playlistService.getAllTracks());
        verify(iPlaylistRepository).findAll();
    }

    /**
     * Method under test: {@link PlaylistService#addTrack(TrackRegisterRequest)}
     */
    @Test
    void testAddTrack() {
        when(iPlaylistRepository.findByTitle((String) any())).thenReturn(mock(Track.class));
        when(iPlaylistRepository.save((Track) any())).thenReturn(1);
        assertThrows(TrackAlreadyExistException.class, () -> playlistService.addTrack(new TrackRegisterRequest("Dr")));
        verify(iPlaylistRepository).findByTitle((String) any());
    }

    /**
     * Method under test: {@link PlaylistService#addTrack(TrackRegisterRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddTrack2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.weedingplaylist.playlist.dto.TrackRegisterRequest.getTitle()" because "trackRegisterRequest" is null
        //       at com.example.weedingplaylist.playlist.service.PlaylistService.addTrack(PlaylistService.java:23)
        //   In order to prevent addTrack(TrackRegisterRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addTrack(TrackRegisterRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        when(iPlaylistRepository.findByTitle((String) any())).thenReturn(mock(Track.class));
        when(iPlaylistRepository.save((Track) any())).thenReturn(1);
        playlistService.addTrack(null);
    }

    /**
     * Method under test: {@link PlaylistService#addTrack(TrackRegisterRequest)}
     */
    @Test
    void testAddTrack3() {
        when(iPlaylistRepository.findByTitle((String) any()))
                .thenThrow(new TrackAlreadyExistException("An error occurred"));
        when(iPlaylistRepository.save((Track) any())).thenThrow(new TrackAlreadyExistException("An error occurred"));
        assertThrows(TrackAlreadyExistException.class, () -> playlistService.addTrack(new TrackRegisterRequest("Dr")));
        verify(iPlaylistRepository).findByTitle((String) any());
    }

    /**
     * Method under test: {@link PlaylistService#removeTrack(Integer)}
     */
    @Test
    void testRemoveTrack() {
        when(iPlaylistRepository.deleteById((Integer) any())).thenReturn(123);
        playlistService.removeTrack(1);
        verify(iPlaylistRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link PlaylistService#removeTrack(Integer)}
     */
    @Test
    void testRemoveTrack2() {
        when(iPlaylistRepository.deleteById((Integer) any()))
                .thenThrow(new TrackAlreadyExistException("An error occurred"));
        assertThrows(TrackAlreadyExistException.class, () -> playlistService.removeTrack(1));
        verify(iPlaylistRepository).deleteById((Integer) any());
    }
}

