package com.finalproject.travelagency.repository;


import com.finalproject.travelagency.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    public Optional<Tour> findTourById(Long id);
}
