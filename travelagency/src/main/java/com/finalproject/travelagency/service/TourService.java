package com.finalproject.travelagency.service;


import com.finalproject.travelagency.exception.TourNotFoundException;
import com.finalproject.travelagency.model.Tour;
import com.finalproject.travelagency.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    private final TourRepository tourRepository;
    @Autowired
    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> getAllTours(){
        return tourRepository.findAll();
    }

    public Tour getTourById(Long id){
        return tourRepository.findTourById(id)
                .orElseThrow(() -> new TourNotFoundException("User with id=" + id + "was not found."));
    }

    public Tour addTour(Tour tour){
        return tourRepository.save(tour);
    }

    public Tour updateTour(Tour tour){
        return tourRepository.save(tour);
    }
    public void deleteTourById(Long id){
        tourRepository.deleteById(id);
    }
}
