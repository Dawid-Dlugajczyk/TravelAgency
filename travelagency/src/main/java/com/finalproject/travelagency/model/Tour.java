package com.finalproject.travelagency.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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
}
