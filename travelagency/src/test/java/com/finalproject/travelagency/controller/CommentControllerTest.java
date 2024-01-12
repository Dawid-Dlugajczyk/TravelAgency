package com.finalproject.travelagency.controller;

import com.finalproject.travelagency.controller.CommentController;
import com.finalproject.travelagency.model.Comment;
import com.finalproject.travelagency.repository.CommentRepository;
import com.finalproject.travelagency.service.TourService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentControllerTest {

    @Mock
    private TourService tourService;
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCommentToTour() throws Exception {
        Long tourId = 1L;
        Long userId = 2L;
        String commentText = "Great tour!";
        Map<String, Object> request = Map.of(
                "userId", userId,
                "commentText", commentText
        );

        doNothing().when(tourService).addCommentToTour(tourId, userId, commentText);

        ResponseEntity<String> response = commentController.addCommentToTour(tourId, request);

        verify(tourService, times(1)).addCommentToTour(tourId, userId, commentText);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Comment added successfully", response.getBody());
    }

    @Test
    void testGetCommentsForTour() {
        Long tourId = 1L;
        Comment comment1 = Comment.builder().id(1L).text("Good tour!").build();
        Comment comment2 = Comment.builder().id(2L).text("Amazing experience!").build();
        List<Comment> comments = Arrays.asList(comment1, comment2);

        // Mocking the service to return comments when getCommentsForTour is called
        when(tourService.getCommentsForTour(tourId)).thenReturn(comments);

        // Invoking the controller method
        ResponseEntity<List<Comment>> response = commentController.getCommentsForTour(tourId);

        // Verifying that the service method was called
        verify(tourService, times(1)).getCommentsForTour(tourId);

        // Assertions
        assertEquals(comments, response.getBody());
    }


    @Test
    void testDeleteComment() {
        Long commentId = 1L;

        doNothing().when(commentRepository).deleteById(commentId);

        ResponseEntity<Comment> response = commentController.deleteComment(commentId);

        verify(commentRepository, times(1)).deleteById(commentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
