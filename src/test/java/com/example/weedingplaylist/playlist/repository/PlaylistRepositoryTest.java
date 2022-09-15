package com.example.weedingplaylist.playlist.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.weedingplaylist.playlist.model.Track;

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

@ContextConfiguration(classes = {PlaylistRepository.class})
@ExtendWith(SpringExtension.class)
class PlaylistRepositoryTest {
    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlaylistRepository playlistRepository;

    /**
     * Method under test: {@link PlaylistRepository#count()}
     */
    @Test
    void testCount() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (Class<Integer>) any())).thenReturn(1);
        assertEquals(1, playlistRepository.count());
        verify(jdbcTemplate).queryForObject((String) any(), (Class<Integer>) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#count()}
     */
    @Test
    void testCount2() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (Class<Integer>) any())).thenReturn(null);
        assertEquals(0, playlistRepository.count());
        verify(jdbcTemplate).queryForObject((String) any(), (Class<Integer>) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#count()}
     */
    @Test
    void testCount3() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (Class<Integer>) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> playlistRepository.count());
        verify(jdbcTemplate).queryForObject((String) any(), (Class<Integer>) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#save(Track)}
     */
    @Test
    void testSave() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenReturn(1);
        Track track = mock(Track.class);
        when(track.getTitle()).thenReturn("Dr");
        assertEquals(1, playlistRepository.save(track));
        verify(jdbcTemplate).update((String) any(), (Object[]) any());
        verify(track).getTitle();
    }

    /**
     * Method under test: {@link PlaylistRepository#save(Track)}
     */
    @Test
    void testSave2() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenReturn(1);
        Track track = mock(Track.class);
        when(track.getTitle()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> playlistRepository.save(track));
        verify(track).getTitle();
    }

    /**
     * Method under test: {@link PlaylistRepository#deleteById(Integer)}
     */
    @Test
    void testDeleteById() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenReturn(1);
        assertEquals(1, playlistRepository.deleteById(1));
        verify(jdbcTemplate).update((String) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#deleteById(Integer)}
     */
    @Test
    void testDeleteById2() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> playlistRepository.deleteById(1));
        verify(jdbcTemplate).update((String) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#findAll()}
     */
    @Test
    void testFindAll() throws DataAccessException {
        ArrayList<Object> objectList = new ArrayList<>();
        when(jdbcTemplate.query((String) any(), (RowMapper<Object>) any())).thenReturn(objectList);
        List<Track> actualFindAllResult = playlistRepository.findAll();
        assertSame(objectList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(jdbcTemplate).query((String) any(), (RowMapper<Object>) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#findAll()}
     */
    @Test
    void testFindAll2() throws DataAccessException {
        when(jdbcTemplate.query((String) any(), (RowMapper<Object>) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> playlistRepository.findAll());
        verify(jdbcTemplate).query((String) any(), (RowMapper<Object>) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#findByTitle(String)}
     */
    @Test
    void testFindByTitle() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any()))
                .thenReturn("Query For Object");
        assertNull(playlistRepository.findByTitle("Dr"));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#findByTitle(String)}
     */
    @Test
    void testFindByTitle2() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertNull(playlistRepository.findByTitle("Dr"));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#findById(Integer)}
     */
    @Test
    void testFindById() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any()))
                .thenReturn("Query For Object");
        assertNull(playlistRepository.findById(1));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link PlaylistRepository#findById(Integer)}
     */
    @Test
    void testFindById2() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertNull(playlistRepository.findById(1));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any());
    }
}

