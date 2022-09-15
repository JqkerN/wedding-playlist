package com.example.weedingplaylist.vote.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.weedingplaylist.exception.GustNotFoundException;
import com.example.weedingplaylist.guest.model.Guest;
import com.example.weedingplaylist.guest.repository.IGuestRepository;
import com.example.weedingplaylist.playlist.model.Track;
import com.example.weedingplaylist.playlist.repository.IPlaylistRepository;
import com.example.weedingplaylist.vote.dto.VoteRequest;
import com.example.weedingplaylist.vote.model.Vote;
import com.example.weedingplaylist.vote.dto.NameTitleVote;
import com.example.weedingplaylist.vote.dto.VoteCount;
import com.example.weedingplaylist.vote.repository.IVoteRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PlaylistVoteService.class})
@ExtendWith(SpringExtension.class)
class PlaylistVoteServiceTest {
    @MockBean
    private IGuestRepository iGuestRepository;

    @MockBean
    private IPlaylistRepository iPlaylistRepository;

    @MockBean
    private IVoteRepository iVoteRepository;

    @Autowired
    private PlaylistVoteService playlistVoteService;

    /**
     * Method under test: {@link PlaylistVoteService#getAllVotes()}
     */
    @Test
    void testGetAllVotes() {
        ArrayList<NameTitleVote> nameTitleVoteList = new ArrayList<>();
        when(iVoteRepository.findAll()).thenReturn(nameTitleVoteList);
        List<NameTitleVote> actualAllVotes = playlistVoteService.getAllVotes();
        assertSame(nameTitleVoteList, actualAllVotes);
        assertTrue(actualAllVotes.isEmpty());
        verify(iVoteRepository).findAll();
    }

    /**
     * Method under test: {@link PlaylistVoteService#getAllVotes()}
     */
    @Test
    void testGetAllVotes2() {
        when(iVoteRepository.findAll()).thenThrow(new GustNotFoundException("An error occurred"));
        assertThrows(GustNotFoundException.class, () -> playlistVoteService.getAllVotes());
        verify(iVoteRepository).findAll();
    }

    /**
     * Method under test: {@link PlaylistVoteService#getAllVotes(Integer)}
     */
    @Test
    void testGetAllVotes3() {
        ArrayList<NameTitleVote> nameTitleVoteList = new ArrayList<>();
        when(iVoteRepository.findAllByTrackId((Integer) any())).thenReturn(nameTitleVoteList);
        List<NameTitleVote> actualAllVotes = playlistVoteService.getAllVotes(123);
        assertSame(nameTitleVoteList, actualAllVotes);
        assertTrue(actualAllVotes.isEmpty());
        verify(iVoteRepository).findAllByTrackId((Integer) any());
    }

    /**
     * Method under test: {@link PlaylistVoteService#getAllVotes(Integer)}
     */
    @Test
    void testGetAllVotes4() {
        when(iVoteRepository.findAllByTrackId((Integer) any())).thenThrow(new GustNotFoundException("An error occurred"));
        assertThrows(GustNotFoundException.class, () -> playlistVoteService.getAllVotes(123));
        verify(iVoteRepository).findAllByTrackId((Integer) any());
    }

    /**
     * Method under test: {@link PlaylistVoteService#getVotesPerTrackIds()}
     */
    @Test
    void testGetVotesPerTrackIds() {
        ArrayList<VoteCount> voteCountList = new ArrayList<>();
        when(iVoteRepository.countGuestIdPerTrackId()).thenReturn(voteCountList);
        List<VoteCount> actualVotesPerTrackIds = playlistVoteService.getVotesPerTrackIds();
        assertSame(voteCountList, actualVotesPerTrackIds);
        assertTrue(actualVotesPerTrackIds.isEmpty());
        verify(iVoteRepository).countGuestIdPerTrackId();
    }

    /**
     * Method under test: {@link PlaylistVoteService#getVotesPerTrackIds()}
     */
    @Test
    void testGetVotesPerTrackIds2() {
        when(iVoteRepository.countGuestIdPerTrackId()).thenThrow(new GustNotFoundException("An error occurred"));
        assertThrows(GustNotFoundException.class, () -> playlistVoteService.getVotesPerTrackIds());
        verify(iVoteRepository).countGuestIdPerTrackId();
    }

    /**
     * Method under test: {@link PlaylistVoteService#addVote(VoteRequest)}
     */
    @Test
    void testAddVote() {
        when(iVoteRepository.save((Vote) any())).thenReturn(1);
        Guest guest = mock(Guest.class);
        when(guest.getId()).thenReturn(1);
        when(iGuestRepository.findByFullName((String) any())).thenReturn(guest);
        Track track = mock(Track.class);
        when(track.getId()).thenReturn(1);
        when(iPlaylistRepository.findByTitle((String) any())).thenReturn(track);
        playlistVoteService.addVote(new VoteRequest("Dr Jane Doe", "Dr"));
        verify(iVoteRepository).save((Vote) any());
        verify(iGuestRepository).findByFullName((String) any());
        verify(guest).getId();
        verify(iPlaylistRepository).findByTitle((String) any());
        verify(track).getId();
    }

    /**
     * Method under test: {@link PlaylistVoteService#addVote(VoteRequest)}
     */
    @Test
    void testAddVote2() {
        when(iVoteRepository.save((Vote) any())).thenReturn(1);
        Guest guest = mock(Guest.class);
        when(guest.getId()).thenReturn(1);
        when(iGuestRepository.findByFullName((String) any())).thenReturn(guest);
        Track track = mock(Track.class);
        when(track.getId()).thenThrow(new GustNotFoundException("An error occurred"));
        when(iPlaylistRepository.findByTitle((String) any())).thenReturn(track);
        assertThrows(GustNotFoundException.class,
                () -> playlistVoteService.addVote(new VoteRequest("Dr Jane Doe", "Dr")));
        verify(iGuestRepository).findByFullName((String) any());
        verify(guest).getId();
        verify(iPlaylistRepository).findByTitle((String) any());
        verify(track).getId();
    }

    /**
     * Method under test: {@link PlaylistVoteService#addVote(VoteRequest)}
     */
    @Test
    void testAddVote3() {
        when(iVoteRepository.save((Vote) any())).thenReturn(0);
        Guest guest = mock(Guest.class);
        when(guest.getId()).thenThrow(new GustNotFoundException("An error occurred"));
        when(iGuestRepository.findByFullName((String) any())).thenReturn(guest);
        when(iPlaylistRepository.findByTitle((String) any())).thenReturn(mock(Track.class));
        assertThrows(GustNotFoundException.class,
                () -> playlistVoteService.addVote(new VoteRequest("Dr Jane Doe", "Dr")));
        verify(iGuestRepository).findByFullName((String) any());
        verify(guest).getId();
        verify(iPlaylistRepository).findByTitle((String) any());
    }

    /**
     * Method under test: {@link PlaylistVoteService#addVote(VoteRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddVote4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.weedingplaylist.vote.dto.VoteRequest.getFullName()" because "voteRequest" is null
        //       at com.example.weedingplaylist.vote.service.PlaylistVoteService.addVote(PlaylistVoteService.java:39)
        //   In order to prevent addVote(VoteRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addVote(VoteRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        when(iVoteRepository.save((Vote) any())).thenReturn(0);
        when(iGuestRepository.findByFullName((String) any())).thenReturn(mock(Guest.class));
        new GustNotFoundException("An error occurred");
        when(iPlaylistRepository.findByTitle((String) any())).thenReturn(mock(Track.class));
        playlistVoteService.addVote(null);
    }

    /**
     * Method under test: {@link PlaylistVoteService#removeVote(Integer)}
     */
    @Test
    void testRemoveVote() {
        when(iVoteRepository.deleteById((Integer) any())).thenReturn(123);
        playlistVoteService.removeVote(1);
        verify(iVoteRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link PlaylistVoteService#removeVote(Integer)}
     */
    @Test
    void testRemoveVote2() {
        when(iVoteRepository.deleteById((Integer) any())).thenThrow(new GustNotFoundException("An error occurred"));
        assertThrows(GustNotFoundException.class, () -> playlistVoteService.removeVote(1));
        verify(iVoteRepository).deleteById((Integer) any());
    }
}

