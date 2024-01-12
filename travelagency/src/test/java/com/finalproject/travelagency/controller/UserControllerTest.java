package com.finalproject.travelagency.controller;
import com.finalproject.travelagency.model.User;
import com.finalproject.travelagency.service.UserService;
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
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = User.builder()
                .email("some")
                .password("some")
                .build();
        User user2 = User.builder()
                .email("test")
                .password("test")
                .build();
        List<User> userList = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void testGetUserById() {
        long userId = 1L;
        User user = User.builder()
                .id(userId)
                .email("test")
                .password("test")
                .build();

        when(userService.findUserById(userId)).thenReturn(user);
        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testAddUser() {
        User user = User.builder()
                .email("test")
                .password("test")
                .build();
        when(userService.addUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.addUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testUpdateUser() {
        long userId = 1L;
        User user = User.builder()
                .id(userId)
                .email("test")
                .password("test")
                .build();

        when(userService.updateUser(user, userId)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(user, userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;

        ResponseEntity<User> response = userController.deleteUser(userId);

        verify(userService, times(1)).deleteUser(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}