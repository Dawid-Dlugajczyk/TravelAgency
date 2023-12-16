package com.finalproject.travelagency.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tours")
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tour_id", nullable=false, updatable = false)
    Long id;

    @Column(name = "trip_name")
    String name;

    @Column
    Double price;

    @Column(name="country")
    String country;

    @Column(name="city")
    String city;

    @Column
    Integer numberOfDays;

    @Column
    LocalDate departureDate;

    @Column
    LocalDate arrivalDate;

    @Column(name = "meal")
    @Enumerated(EnumType.STRING)
    MealType meal;

    @Column
    String hotelName;

    @Column
    String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    TourType type;

    @Column(length = 20971520)
    @Lob
    byte[] image;

    @Column
    Integer availablePlaces;

}
