package com.finalproject.travelagency.repository;


import com.finalproject.travelagency.model.MealType;
import com.finalproject.travelagency.model.Tour;
import com.finalproject.travelagency.model.TourType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    Optional<Tour> findTourById(Long id);

    @Query("SELECT t FROM Tour t WHERE " +
            "(:countries IS NULL OR t.country IN :countries) " +
            "AND (:cities IS NULL OR t.city IN :cities) " +
            "AND (:departureDate IS NULL OR t.departureDate >= :departureDate) " +
            "AND (:meals IS NULL OR t.meal IN :meals) " +
            "AND ((:hotelName IS NULL OR t.hotelName = :hotelName) OR (:hotelName = '')) " +
            "AND (:arrivalDate IS NULL OR t.arrivalDate >= :arrivalDate) " +
            "AND (:types IS NULL OR t.type IN :types) " +
            "AND ((:name IS NULL OR t.name = :name) OR (:name = '' )) " +
            "AND (:minPrice IS NULL OR t.price >= :minPrice)" +
            "AND (:maxPrice IS NULL OR t.price <= :maxPrice)"+
            "AND (:minNumOfDays IS NULL OR t.numberOfDays >= :minNumOfDays)" +
            "AND (:maxNumOfDays IS NULL OR t.numberOfDays <= :maxNumOfDays)")
    List<Tour> findByFilters(
            @Param("countries") List<String> countries,
            @Param("cities") List<String> cities,
            @Param("departureDate") LocalDate departureDate,
            @Param("meals") List<MealType> meals,
            @Param("hotelName") String hotelName,
            @Param("arrivalDate") LocalDate arrivalDate,
            @Param("types") List<TourType> types,
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minNumOfDays") Integer minNumOfDays,
            @Param("maxNumOfDays") Integer maxNumOfDays);
}

