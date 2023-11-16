package com.finalproject.travelagency.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tours")
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "trip_name")
    String name;
}
