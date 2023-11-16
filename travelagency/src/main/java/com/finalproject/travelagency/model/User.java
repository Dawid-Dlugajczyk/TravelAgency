package com.finalproject.travelagency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(nullable=false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="city")
    private String city;

    @Column(name="addres")
    private String address;

    @Column(name="pesel")
    private String pesel;

    @Column(name="email")
    private String email;

    @Column(updatable = false, nullable = false)
    private String UserCode;
}

