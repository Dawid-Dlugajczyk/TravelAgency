package com.finalproject.travelagency.service;

import com.finalproject.travelagency.exception.ReservationNotFoundException;
import com.finalproject.travelagency.model.Reservation;
import com.finalproject.travelagency.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    public List<Reservation> getReservationsByUserId(Long id){
        return reservationRepository.getReservationsByUserId(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation with id=" + id + " was not found."));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
