package com.finalproject.travelagency.service;


import com.finalproject.travelagency.exception.TourNotFoundException;
import com.finalproject.travelagency.model.MealType;
import com.finalproject.travelagency.model.Tour;
import com.finalproject.travelagency.model.TourType;
import com.finalproject.travelagency.repository.TourRepository;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Tour addTour(Tour tour, MultipartFile imageFile) throws IOException {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                byte[] imageData = imageFile.getBytes();
                tour.setImage(imageData);
            }
            return tourRepository.save(tour);
        } catch (IOException e) {
            // Handle IO exception
            throw new RuntimeException("Failed to process image file.", e);
        }
    }

    public Tour updateTour(Tour tour, MultipartFile imageFile, Long id) throws IOException {
        try {
            Optional<Tour> existingTour = tourRepository.findById(id);
            if (existingTour.isPresent()) {
                Tour foundTour = existingTour.get();
                if (imageFile != null && !imageFile.isEmpty()) {
                    foundTour.setImage(imageFile.getBytes());
                }
                foundTour.setCity(tour.getCity());
                foundTour.setCountry(tour.getCountry());
                foundTour.setDescription(tour.getDescription());
                foundTour.setArrivalDate(tour.getArrivalDate());
                foundTour.setDepartureDate(tour.getDepartureDate());
                foundTour.setHotelName(tour.getHotelName());
                foundTour.setMeal(tour.getMeal());
                foundTour.setName(tour.getName());
                foundTour.setPrice(tour.getPrice());
                foundTour.setType(tour.getType());

                return tourRepository.save(foundTour);

            }else{
                return tourRepository.save(tour);
            }

        } catch (IOException e) {
            // Handle IO exception
            throw new RuntimeException("Failed to process image file.", e);
        }
    }
    public void deleteTourById(Long id){
        tourRepository.deleteById(id);
    }

    public List<Tour> filterTours(List<String> countries, List<String> cities, LocalDate departureDate,
                                  List<MealType> meals, String hotelName, LocalDate arrivalDate,
                                  List<TourType> types, String name, Double minPrice, Double maxPrice,Integer minNumberOfDays, Integer maxNumOfDays) {
        return tourRepository.findByFilters(countries, cities, departureDate, meals, hotelName, arrivalDate,
                types, name, minPrice, maxPrice, minNumberOfDays, maxNumOfDays);
    }

    public List<String> getMealTypes() {
        return Arrays.stream(MealType.values())
                .map(Enum::name) // Convert enum to string
                .collect(Collectors.toList());
    }

/*    public List<String> getTourTypes() {
        return Arrays.stream(TourType.values())
                .map(Enum::name) // Convert enum to string
                .collect(Collectors.toList());
    }*/

    public List<TourType> getTourTypes() {
        return Arrays.asList(TourType.values());
    }
    public List<String> getAllCountries() {
        return tourRepository.findAll().stream()
                .map(Tour::getCountry)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllCities() {
        return tourRepository.findAll().stream()
                .map(Tour::getCity)
                .distinct()
                .collect(Collectors.toList());
    }

    public TourType convertEnum(TourType tourType){
        TourType convertedType = TourType.ADVENTURE;
        String type = tourType.toString();
        switch (type){
            case "HIKING":
                convertedType = TourType.HIKING;
            case "ADVENTURE":
                convertedType = TourType.ADVENTURE;
            case "CULTURAL":
                convertedType = TourType.CULTURAL;
            case "BEACH":
                convertedType = TourType.BEACH;
            case "RELAXATION":
                convertedType =  TourType.RELAXATION;

        }
        return convertedType;
    }

}
