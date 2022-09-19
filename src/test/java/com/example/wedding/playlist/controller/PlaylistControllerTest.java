package com.example.wedding.playlist.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.wedding.playlist.dto.TrackRegisterRequest;
import com.example.wedding.playlist.service.PlaylistService;
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

@ContextConfiguration(classes = {PlaylistController.class})
@ExtendWith(SpringExtension.class)
class PlaylistControllerTest {
    @Autowired
    private PlaylistController playlistController;

    @MockBean
    private PlaylistService playlistService;

    /**
     * Method under test: {@link PlaylistController#addTrack(TrackRegisterRequest)}
     */
    @Test
    void testAddTrack() throws Exception {
        when(playlistService.getAllTracks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/playlist")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new TrackRegisterRequest()));
        MockMvcBuilders.standaloneSetup(playlistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlaylistController#removeTrack(Integer)}
     */
    @Test
    void testRemoveTrack() throws Exception {
        doNothing().when(playlistService).removeTrack((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/playlist/{id}", 1);
        MockMvcBuilders.standaloneSetup(playlistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PlaylistController#removeTrack(Integer)}
     */
    @Test
    void testRemoveTrack2() throws Exception {
        doNothing().when(playlistService).removeTrack((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/playlist/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(playlistController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PlaylistController#addTrack(TrackRegisterRequest)}
     */
    @Test
    void testAddTrack2() throws Exception {
        when(playlistService.getAllTracks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/playlist");
        getResult.characterEncoding("Encoding");
        MockHttpServletRequestBuilder contentTypeResult = getResult.contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new TrackRegisterRequest()));
        MockMvcBuilders.standaloneSetup(playlistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlaylistController#allTracks()}
     */
    @Test
    void testAllTracks() throws Exception {
        when(playlistService.getAllTracks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/playlist");
        MockMvcBuilders.standaloneSetup(playlistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlaylistController#allTracks()}
     */
    @Test
    void testAllTracks2() throws Exception {
        when(playlistService.getAllTracks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/playlist");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(playlistController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

