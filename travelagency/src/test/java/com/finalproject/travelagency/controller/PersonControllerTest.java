package com.finalproject.travelagency.controller;

import com.finalproject.travelagency.controller.PersonController;
import com.finalproject.travelagency.model.Person;
import com.finalproject.travelagency.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonControllerTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPersonsByReservationId() {
        Long reservationId = 1L;
        List<Person> persons = Arrays.asList(
                new Person(1L, "John", "Doe", null, "12345", null),
                new Person(2L, "Jane", "Doe", null, "67890", null)
        );

        when(personRepository.findByReservationId(reservationId)).thenReturn(persons);

        ResponseEntity<List<Person>> response = personController.getPersonsByReservationId(reservationId);

        verify(personRepository, times(1)).findByReservationId(reservationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(persons, response.getBody());
    }
}
