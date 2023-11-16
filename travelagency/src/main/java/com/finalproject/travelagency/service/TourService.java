package com.finalproject.travelagency.service;


import com.finalproject.travelagency.model.Tour;
import com.finalproject.travelagency.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TourService {

    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> getAllTours(){
        return tourRepository.findAll();
    }
}
