package com.finalproject.travelagency.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproject.travelagency.model.MealType;
import com.finalproject.travelagency.model.Tour;
import com.finalproject.travelagency.model.TourType;
import com.finalproject.travelagency.service.TourService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourControllerTest {

    @Mock
    private TourService tourService;

    @InjectMocks
    private TourController tourController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTours() {

        Tour tour1 = Tour.builder()
                .name("TestTrip")
                .city("Test")
                .build();
        Tour tour2 = Tour.builder()
                .name("TestTrip1")
                .city("Test")
                .build();
        List<Tour> tours = Arrays.asList(tour1, tour2);
        when(tourService.getAllTours()).thenReturn(tours);

        ResponseEntity<List<Tour>> response = tourController.getAllTours();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tours, response.getBody());
    }

    @Test
    void testGetTourById() {
        Long id = 1L;
        Tour tour = Tour.builder()
                .id(id)
                .name("TestTrip")
                .city("Test")
                .build();
        when(tourService.getTourById(id)).thenReturn(tour);

        ResponseEntity<Tour> response = tourController.getTourById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tour, response.getBody());
    }

    @Test
    void testAddTour() throws IOException {
        Tour tour = Tour.builder()
                .name("TestTrip")
                .city("Test")
                .build();

        MultipartFile imageFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", new byte[0]);

        when(tourService.addTour(any(Tour.class), any(MultipartFile.class))).thenReturn(tour);

        ResponseEntity<Tour> response = tourController.addTour(toJson(tour), imageFile);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tour, response.getBody());
    }

    @Test
    void testUpdateTour() throws IOException {
        Long id = 1L;
        Tour tour = Tour.builder()
                .id(id)
                .name("TestTrip")
                .city("Test")
                .build();
        MultipartFile imageFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", new byte[0]);

        when(tourService.updateTour(any(Tour.class), any(MultipartFile.class), eq(id))).thenReturn(tour);

        ResponseEntity<Tour> response = tourController.updateTour(toJson(tour), imageFile, id);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tour, response.getBody());
    }

    @Test
    void testDeleteTourById() {
        Long id = 1L;

        ResponseEntity<Tour> response = tourController.deleteTourById(id);

        verify(tourService, times(1)).deleteTourById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testFilterTours() {
        List<Tour> filteredTours = Arrays.asList(
                Tour.builder()
                        .name("Tour1")
                        .price(200.0)
                        .country("Country1")
                        .city("City1")
                        .numberOfDays(5)
                        .departureDate(LocalDate.now())
                        .arrivalDate(LocalDate.now())
                        .meal(MealType.AI)
                        .hotelName("Hotel1")
                        .type(TourType.ADVENTURE)
                        .build(),
                Tour.builder()
                        .name("Tour2")
                        .price(300.0)
                        .country("Country2")
                        .city("City2")
                        .numberOfDays(7)
                        .departureDate(LocalDate.now())
                        .arrivalDate(LocalDate.now())
                        .meal(MealType.AI)
                        .hotelName("Hotel2")
                        .type(TourType.ADVENTURE)
                        .build()
        );
        when(tourService.filterTours(anyList(), anyList(), any(), anyList(), any(), any(), anyList(), any(), anyDouble(), anyDouble(), anyInt(), anyInt()))
                .thenReturn(filteredTours);

        ResponseEntity<List<Tour>> response = tourController.filterTours(
                Collections.singletonList("Country1"),
                Collections.singletonList("City1"),
                LocalDate.now(),
                Collections.singletonList(MealType.AI),
                "Hotel1",
                LocalDate.now(),
                Collections.singletonList(TourType.ADVENTURE),
                "TourName",
                100.0,
                500.0,
                3,
                7
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filteredTours, response.getBody());
    }

    @Test
    void testGetToursByDepartureDate() {
        List<Tour> tours = Arrays.asList(
                Tour.builder().id(1L).name("Tour1").departureDate(LocalDate.now()).build(),
                Tour.builder().id(2L).name("Tour2").departureDate(LocalDate.now()).build()
        );
        when(tourService.getToursBeforeOrOnCurrentDate()).thenReturn(tours);

        ResponseEntity<List<Tour>> response = tourController.getToursByDepartureDate();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tours, response.getBody());
    }

    @Test
    void testGetTourTypes() {
        List<TourType> tourTypes = Arrays.asList(TourType.BEACH, TourType.ADVENTURE);
        when(tourService.getTourTypes()).thenReturn(tourTypes);

        ResponseEntity<List<TourType>> response = tourController.getTourTypes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tourTypes, response.getBody());
    }

    @Test
    void testGetMealTypes() {
        List<MealType> mealTypes = Arrays.asList(MealType.BB, MealType.FB, MealType.AI);
        when(tourService.getMealTypes()).thenReturn(mealTypes);

        ResponseEntity<List<MealType>> response = tourController.getMealTypes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mealTypes, response.getBody());
    }

    @Test
    void testGetAllCountries() {
        List<String> countries = Arrays.asList("Country1", "Country2");
        when(tourService.getAllCountries()).thenReturn(countries);

        ResponseEntity<List<String>> response = tourController.getAllCountries();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(countries, response.getBody());
    }

    @Test
    void testGetAllCities() {
        List<String> cities = Arrays.asList("City1", "City2");
        when(tourService.getAllCities()).thenReturn(cities);

        ResponseEntity<List<String>> response = tourController.getAllCities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cities, response.getBody());
    }

private String toJson(Object object) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
}
}