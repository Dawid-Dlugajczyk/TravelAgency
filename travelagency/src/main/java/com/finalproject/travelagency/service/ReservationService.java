package com.finalproject.travelagency.service;

import com.finalproject.travelagency.exception.ReservationNotFoundException;
import com.finalproject.travelagency.model.Person;
import com.finalproject.travelagency.model.Reservation;
import com.finalproject.travelagency.model.Tour;
import com.finalproject.travelagency.model.User;
import com.finalproject.travelagency.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByUserId(Long id) {
        return reservationRepository.getReservationsByUserId(id);

    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    public Reservation addPersonsToReservation(Long reservationId, List<Person> persons) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + reservationId));

        if (reservation.getPersons() == null) {
            reservation.setPersons(new ArrayList<>());
        }

        persons.forEach(person -> {
            person.setReservation(reservation);
            reservation.getPersons().add(person);
        });

        return reservationRepository.save(reservation);
    }

}
