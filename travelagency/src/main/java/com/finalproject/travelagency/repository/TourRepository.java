package com.finalproject.travelagency.repository;


import com.finalproject.travelagency.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
}
