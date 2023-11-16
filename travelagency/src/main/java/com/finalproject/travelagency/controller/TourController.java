package com.finalproject.travelagency.controller;


import com.finalproject.travelagency.model.Tour;
import com.finalproject.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    private final TourService tourService;


    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tour>> getAllTours(){
        List<Tour> tours = tourService.getAllTours();
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }
}
