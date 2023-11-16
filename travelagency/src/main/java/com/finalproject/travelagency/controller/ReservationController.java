package com.finalproject.travelagency.controller;

import com.finalproject.travelagency.model.Reservation;
import com.finalproject.travelagency.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/admin/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable Long id){
        List<Reservation> reservations = reservationService.getReservationsByUserId(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.findReservationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        Reservation newReservation = reservationService.addReservation(reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(reservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

