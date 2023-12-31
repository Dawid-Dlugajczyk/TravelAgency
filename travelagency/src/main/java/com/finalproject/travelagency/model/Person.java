package com.finalproject.travelagency.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "person_id")
    private Long id;

    @Column
    String firstName;

    @Column
    String secondName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

}
