package com.example.wedding.vote.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.wedding.vote.model.Vote;
import com.example.wedding.vote.dto.NameTitleVote;
import com.example.wedding.vote.dto.VoteCount;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {VoteRepository.class})
@ExtendWith(SpringExtension.class)
class VoteRepositoryTest {
    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private VoteRepository voteRepository;

    /**
     * Method under test: {@link VoteRepository#findAll()}
     */
    @Test
    void testFindAll() throws DataAccessException {
        ArrayList<Object> objectList = new ArrayList<>();
        when(jdbcTemplate.query((String) any(), (RowMapper<Object>) any())).thenReturn(objectList);
        List<NameTitleVote> actualFindAllResult = voteRepository.findAll();
        assertSame(objectList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(jdbcTemplate).query((String) any(), (RowMapper<Object>) any());
    }

    /**
     * Method under test: {@link VoteRepository#findAllByTrackId(Integer)}
     */
    @Test
    void testFindAllByTrackId() throws DataAccessException {
        ArrayList<Object> objectList = new ArrayList<>();
        when(jdbcTemplate.query((String) any(), (RowMapper<Object>) any(), (Object[]) any())).thenReturn(objectList);
        List<NameTitleVote> actualFindAllByTrackIdResult = voteRepository.findAllByTrackId(123);
        assertSame(objectList, actualFindAllByTrackIdResult);
        assertTrue(actualFindAllByTrackIdResult.isEmpty());
        verify(jdbcTemplate).query((String) any(), (RowMapper<Object>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link VoteRepository#countGuestIdPerTrackId()}
     */
    @Test
    void testCountGuestIdPerTrackId() throws DataAccessException {
        ArrayList<Object> objectList = new ArrayList<>();
        when(jdbcTemplate.query((String) any(), (RowMapper<Object>) any())).thenReturn(objectList);
        List<VoteCount> actualCountGuestIdPerTrackIdResult = voteRepository.countGuestIdPerTrackId();
        assertSame(objectList, actualCountGuestIdPerTrackIdResult);
        assertTrue(actualCountGuestIdPerTrackIdResult.isEmpty());
        verify(jdbcTemplate).query((String) any(), (RowMapper<Object>) any());
    }

    /**
     * Method under test: {@link VoteRepository#save(Vote)}
     */
    @Test
    void testSave() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenReturn(1);
        Vote vote = mock(Vote.class);
        when(vote.getGuestId()).thenReturn(123);
        when(vote.getTrackId()).thenReturn(123);
        assertEquals(1, voteRepository.save(vote));
        verify(jdbcTemplate).update((String) any(), (Object[]) any());
        verify(vote).getGuestId();
        verify(vote).getTrackId();
    }

    /**
     * Method under test: {@link VoteRepository#deleteById(Integer)}
     */
    @Test
    void testDeleteById() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenReturn(1);
        assertEquals(1, voteRepository.deleteById(1));
        verify(jdbcTemplate).update((String) any(), (Object[]) any());
    }
}

