package com.example.wedding.vote.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.wedding.vote.dto.VoteRequest;
import com.example.wedding.vote.service.PlaylistVoteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PlaylistVoteController.class})
@ExtendWith(SpringExtension.class)
class PlaylistVoteControllerTest {
    @Autowired
    private PlaylistVoteController playlistVoteController;

    @MockBean
    private PlaylistVoteService playlistVoteService;

    /**
     * Method under test: {@link PlaylistVoteController#getAllVotes()}
     */
    @Test
    void testGetAllVotes() throws Exception {
        when(playlistVoteService.getAllVotes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/playlist/votes");
        MockMvcBuilders.standaloneSetup(playlistVoteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlaylistVoteController#getAllVotes()}
     */
    @Test
    void testGetAllVotes2() throws Exception {
        when(playlistVoteService.getAllVotes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/playlist/votes");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(playlistVoteController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlaylistVoteController#addVote(VoteRequest)}
     */
    @Test
    void testAddVote() throws Exception {
        doNothing().when(playlistVoteService).addVote((VoteRequest) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/v1/playlist/votes")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new VoteRequest()));
        MockMvcBuilders.standaloneSetup(playlistVoteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PlaylistVoteController#removeVote(Integer)}
     */
    @Test
    void testRemoveVote() throws Exception {
        doNothing().when(playlistVoteService).removeVote((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/playlist/votes/{id}", 1);
        MockMvcBuilders.standaloneSetup(playlistVoteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PlaylistVoteController#removeVote(Integer)}
     */
    @Test
    void testRemoveVote2() throws Exception {
        doNothing().when(playlistVoteService).removeVote((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/playlist/votes/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(playlistVoteController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PlaylistVoteController#getAllCountPerTrack()}
     */
    @Test
    void testGetAllCountPerTrack() throws Exception {
        when(playlistVoteService.getVotesPerTrackIds()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/playlist/votes/count");
        MockMvcBuilders.standaloneSetup(playlistVoteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlaylistVoteController#getAllCountPerTrack()}
     */
    @Test
    void testGetAllCountPerTrack2() throws Exception {
        when(playlistVoteService.getVotesPerTrackIds()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/playlist/votes/count");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(playlistVoteController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlaylistVoteController#getTrackVotes(Integer)}
     */
    @Test
    void testGetTrackVotes() throws Exception {
        when(playlistVoteService.getAllVotes((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/playlist/{trackId}/votes",
                123);
        MockMvcBuilders.standaloneSetup(playlistVoteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlaylistVoteController#getTrackVotes(Integer)}
     */
    @Test
    void testGetTrackVotes2() throws Exception {
        when(playlistVoteService.getAllVotes()).thenReturn(new ArrayList<>());
        when(playlistVoteService.getAllVotes((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/playlist/{trackId}/votes", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(playlistVoteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

