package com.finalproject.travelagency.controller;
import com.finalproject.travelagency.controller.StatisticsController;
import com.finalproject.travelagency.model.Tour;
import com.finalproject.travelagency.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StatisticsControllerTest {

    @Mock
    private StatisticsService statisticsService;

    @InjectMocks
    private StatisticsController statisticsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTopReservedTours() {
        List<Tour> topReservedTours = Arrays.asList(
                Tour.builder().id(1L).name("Tour1").price(100.0).build(),
                Tour.builder().id(2L).name("Tour2").price(150.0).build()
        );

        when(statisticsService.getTopReservedTours()).thenReturn(topReservedTours);

        ResponseEntity<List<Tour>> response = statisticsController.getTopReservedTours();

        verify(statisticsService, times(1)).getTopReservedTours();

        assertEquals(topReservedTours, response.getBody());
    }

    @Test
    void testGetTopDestinations() {
        List<String> topDestinations = Arrays.asList("Destination1", "Destination2");

        when(statisticsService.getTopDestinations()).thenReturn(topDestinations);

        ResponseEntity<List<String>> response = statisticsController.getTopDestinations();

        verify(statisticsService, times(1)).getTopDestinations();

        assertEquals(topDestinations, response.getBody());
    }

    @Test
    void testGetMostPopularNumberOfDays() {
        List<Integer> popularNumberOfDays = Arrays.asList(7, 10, 14);

        when(statisticsService.getMostPopularNumberOfDays()).thenReturn(popularNumberOfDays);

        ResponseEntity<List<Integer>> response = statisticsController.getMostPopularNumberOfDays();

        verify(statisticsService, times(1)).getMostPopularNumberOfDays();

        assertEquals(popularNumberOfDays, response.getBody());
    }

    @Test
    void testGetMostReservedYearMonth() {
        List<String> mostReservedYearMonth = Arrays.asList("2023-01", "2023-02", "2023-03");

        when(statisticsService.getYearMonthWithMostReservations()).thenReturn(mostReservedYearMonth);

        ResponseEntity<List<String>> response = statisticsController.getMostReservedYearMonth();

        verify(statisticsService, times(1)).getYearMonthWithMostReservations();

        assertEquals(mostReservedYearMonth, response.getBody());
    }
}
