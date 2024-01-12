package com.finalproject.travelagency.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproject.travelagency.model.*;
import com.finalproject.travelagency.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReservations() {
        List<Reservation> reservations = Arrays.asList(
                Reservation.builder().id(1L).build(),
                Reservation.builder().id(2L).build()
        );
        when(reservationService.getAllReservations()).thenReturn(reservations);

        ResponseEntity<List<Reservation>> response = reservationController.getAllReservations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
    }

    @Test
    void testGetReservationsByUserId() {
        Long userId = 1L;
        List<Reservation> reservations = Arrays.asList(
                Reservation.builder().id(1L).build(),
                Reservation.builder().id(2L).build()
        );
        when(reservationService.getReservationsByUserId(userId)).thenReturn(reservations);

        ResponseEntity<List<Reservation>> response = reservationController.getReservationsByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
    }

    @Test
    void testCreateReservation() {
        Reservation reservation = Reservation.builder().id(1L).build();
        when(reservationService.createReservation(reservation)).thenReturn(reservation);

        ResponseEntity<Reservation> response = reservationController.createReservation(reservation);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reservation, response.getBody());
    }

    @Test
    void testUpdateReservation() {
        Long reservationId = 1L;
        Reservation reservation = Reservation.builder().id(reservationId).status(ReservationStatus.CONFIRMED).build();
        when(reservationService.updateReservation(eq(reservationId), any())).thenReturn(reservation);

        ResponseEntity<Reservation> response = reservationController.createReservation(reservationId, reservation);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reservation, response.getBody());
    }

    @Test
    void testGetReservationById() {
        Long reservationId = 1L;
        Reservation reservation = Reservation.builder().id(reservationId).build();
        when(reservationService.getReservationById(reservationId)).thenReturn(reservation);

        ResponseEntity<Reservation> response = reservationController.getReservationById(reservationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservation, response.getBody());
    }

    @Test
    void testDeleteReservationById() {
        Long reservationId = 1L;
        ResponseEntity<Void> response = reservationController.deleteReservationById(reservationId);

        verify(reservationService, times(1)).deleteReservationById(reservationId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
