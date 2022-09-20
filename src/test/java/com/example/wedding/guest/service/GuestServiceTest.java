package com.example.wedding.guest.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.wedding.guest.dto.RegistrationRequest;
import com.example.wedding.guest.model.Guest;
import com.example.wedding.guest.repository.IGuestRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GuestService.class})
@ExtendWith(SpringExtension.class)
class GuestServiceTest {
    @Autowired
    private GuestService guestService;

    @MockBean
    private IGuestRepository iGuestRepository;

    /**
     * Method under test: {@link GuestService#getGuestList()}
     */
    @Test
    void testGetGuestList() {
        ArrayList<Guest> guestList = new ArrayList<>();
        when(iGuestRepository.findAll()).thenReturn(guestList);
        List<Guest> actualGuestList = guestService.getGuestList();
        assertSame(guestList, actualGuestList);
        assertTrue(actualGuestList.isEmpty());
        verify(iGuestRepository).findAll();
    }


    /**
     * Method under test: {@link GuestService#removeGuest(Integer)}
     */
    @Test
    void testRemoveGuest() {
        when(iGuestRepository.deleteById((Integer) any())).thenReturn(123);
        guestService.removeGuest(1);
        verify(iGuestRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link GuestService#getGuest(Integer)}
     */
    @Test
    void testGetGuest() {
        when(iGuestRepository.findById((Integer) any())).thenReturn(mock(Guest.class));
        guestService.getGuest(1);
        verify(iGuestRepository).findById((Integer) any());
    }
}

