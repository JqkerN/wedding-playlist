package com.example.wedding.guest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.wedding.guest.model.Guest;

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

@ContextConfiguration(classes = {GuestRepository.class})
@ExtendWith(SpringExtension.class)
class GuestRepositoryTest {
    @Autowired
    private GuestRepository guestRepository;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    /**
     * Method under test: {@link GuestRepository#save(Guest)}
     */
    @Test
    void testSave() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenReturn(1);
        Guest guest = mock(Guest.class);
        when(guest.getFullName()).thenReturn("Dr Jane Doe");
        assertEquals(1, guestRepository.save(guest));
        verify(jdbcTemplate).update((String) any(), (Object[]) any());
        verify(guest).getFullName();
    }

    /**
     * Method under test: {@link GuestRepository#save(Guest)}
     */
    @Test
    void testSave2() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenReturn(1);
        Guest guest = mock(Guest.class);
        when(guest.getFullName()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> guestRepository.save(guest));
        verify(guest).getFullName();
    }

    /**
     * Method under test: {@link GuestRepository#deleteById(Integer)}
     */
    @Test
    void testDeleteById() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenReturn(1);
        assertEquals(1, guestRepository.deleteById(1));
        verify(jdbcTemplate).update((String) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link GuestRepository#deleteById(Integer)}
     */
    @Test
    void testDeleteById2() throws DataAccessException {
        when(jdbcTemplate.update((String) any(), (Object[]) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> guestRepository.deleteById(1));
        verify(jdbcTemplate).update((String) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link GuestRepository#findAll()}
     */
    @Test
    void testFindAll() throws DataAccessException {
        ArrayList<Object> objectList = new ArrayList<>();
        when(jdbcTemplate.query((String) any(), (RowMapper<Object>) any())).thenReturn(objectList);
        List<Guest> actualFindAllResult = guestRepository.findAll();
        assertSame(objectList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(jdbcTemplate).query((String) any(), (RowMapper<Object>) any());
    }

    /**
     * Method under test: {@link GuestRepository#findAll()}
     */
    @Test
    void testFindAll2() throws DataAccessException {
        when(jdbcTemplate.query((String) any(), (RowMapper<Object>) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> guestRepository.findAll());
        verify(jdbcTemplate).query((String) any(), (RowMapper<Object>) any());
    }

    /**
     * Method under test: {@link GuestRepository#findById(Integer)}
     */
    @Test
    void testFindById() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any()))
                .thenReturn("Query For Object");
        assertNull(guestRepository.findById(1));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link GuestRepository#findById(Integer)}
     */
    @Test
    void testFindById2() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertNull(guestRepository.findById(1));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link GuestRepository#findByFullName(String)}
     */
    @Test
    void testFindByFullName() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any()))
                .thenReturn("Query For Object");
        assertNull(guestRepository.findByFullName("Dr Jane Doe"));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link GuestRepository#findByFullName(String)}
     */
    @Test
    void testFindByFullName2() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertNull(guestRepository.findByFullName("Dr Jane Doe"));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Object>) any(), (Object[]) any());
    }
}

