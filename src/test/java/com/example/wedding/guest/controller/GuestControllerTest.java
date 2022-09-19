package com.example.wedding.guest.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.wedding.guest.dto.RegistrationRequest;
import com.example.wedding.guest.model.Guest;
import com.example.wedding.guest.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {GuestController.class})
@ExtendWith(SpringExtension.class)
class GuestControllerTest {
    @Autowired
    private GuestController guestController;

    @MockBean
    private GuestService guestService;

    /**
     * Method under test: {@link GuestController#getAllGuests()}
     */
    @Test
    void testGetAllGuests() throws Exception {
        when(guestService.getGuestList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/guests");
        MockMvcBuilders.standaloneSetup(guestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GuestController#getGuest(Integer)}
     */
    @Test
    void testGetGuest() throws Exception {
        when(guestService.getGuest((Integer) any())).thenReturn(mock(Guest.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/guests/{id}", "Uri Variables",
                "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(guestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link GuestController#getGuest(Integer)}
     */
    @Test
    void testGetGuest2() throws Exception {
        when(guestService.getGuest((Integer) any())).thenReturn(mock(Guest.class));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/guests/{id}", 1);
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(guestController).build().perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link GuestController#getAllGuests()}
     */
    @Test
    void testGetAllGuests2() throws Exception {
        when(guestService.getGuestList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/guests");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(guestController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GuestController#registerGuest(RegistrationRequest)}
     */
    @Test
    void testRegisterGuest() throws Exception {
        when(guestService.getGuestList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/guests")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new RegistrationRequest()));
        MockMvcBuilders.standaloneSetup(guestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GuestController#registerGuest(RegistrationRequest)}
     */
    @Test
    void testRegisterGuest2() throws Exception {
        when(guestService.getGuestList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/guests");
        getResult.characterEncoding("Encoding");
        MockHttpServletRequestBuilder contentTypeResult = getResult.contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new RegistrationRequest()));
        MockMvcBuilders.standaloneSetup(guestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GuestController#unregisterGuest(Integer)}
     */
    @Test
    void testUnregisterGuest() throws Exception {
        doNothing().when(guestService).removeGuest((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/guests/{id}", 1);
        MockMvcBuilders.standaloneSetup(guestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link GuestController#unregisterGuest(Integer)}
     */
    @Test
    void testUnregisterGuest2() throws Exception {
        doNothing().when(guestService).removeGuest((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/guests/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(guestController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

