package com.finalproject.travelagency.controller;


import com.finalproject.travelagency.model.Tour;
import com.finalproject.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.metadata.GenericCallMetaDataProvider;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/admin/tours")
public class TourController {

    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tour>> getAllTours(){
        List<Tour> tours = tourService.getAllTours();
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable Long id){
        Tour tour = tourService.getTourById(id);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Tour> addTour(@RequestBody Tour tour){
        Tour newTour = tourService.addTour(tour);
        return new ResponseEntity<>(tour, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Tour> updateTour(@RequestBody Tour tour){
        Tour newTour = tourService.updateTour(tour);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Tour> deleteTourById(@PathVariable Long id){
        tourService.deleteTourById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
