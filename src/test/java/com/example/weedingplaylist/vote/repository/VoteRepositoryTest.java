package com.example.weedingplaylist.vote.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.weedingplaylist.vote.model.IdVote;
import com.example.weedingplaylist.vote.model.NameTitleVote;
import com.example.weedingplaylist.vote.model.VoteCount;

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
     * Method under test: {@link VoteRepository#save(IdVote)}
     */
    @Test
    void testSave() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenReturn(1);
        IdVote idVote = mock(IdVote.class);
        when(idVote.getGuestId()).thenReturn(123);
        when(idVote.getTrackId()).thenReturn(123);
        assertEquals(1, voteRepository.save(idVote));
        verify(jdbcTemplate).update((String) any(), (Object[]) any());
        verify(idVote).getGuestId();
        verify(idVote).getTrackId();
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

