package com.MGL_Task4.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.MGL_Task4.manager.ReviewManagerImpl;
import com.MGL_Task4.model.Review;

@RunWith(MockitoJUnitRunner.Silent.class)
public class Review_Controller_Test {

    @Mock
    ReviewManagerImpl reviewManagerMock = Mockito.mock(ReviewManagerImpl.class);

    @InjectMocks
    Review_Controller review_Controller;

    @BeforeMethod
    public void setup() {
	MockitoAnnotations.initMocks(this);
    }

    private Review setReviewObjectForTest() {
	Review testReview = new Review();
	testReview.setReview_author("testAuthor");
	testReview.setReview_body("testBody");
	testReview.setReview_game_id(Integer.valueOf(1));
	testReview.setReview_id(Long.valueOf(1));
	testReview.setReview_rating(Integer.valueOf(5));
	return testReview;
    }

    @Test
    public void listReviewsTest() {
	Review testReview = setReviewObjectForTest();
	Review testReview2 = setReviewObjectForTest();
	testReview2.setReview_id(Long.valueOf(2));
	List<Review> reviewList = new ArrayList<>();
	reviewList.add(testReview);
	reviewList.add(testReview2);
	Mockito.when(reviewManagerMock.getReviews(ArgumentMatchers.anyLong())).thenReturn(reviewList);
	review_Controller.fetchReviewsForGame(testReview.getReview_game_id().toString());
    }

    @Test
    public void createReviewTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewManagerMock.saveReview(ArgumentMatchers.any(Review.class))).thenReturn(testReview);
	ResponseEntity<Review> reviewResponseEntity = new ResponseEntity<>(testReview, HttpStatus.OK);
	Assertions.assertEquals(reviewResponseEntity, review_Controller.addReview(testReview));
    }

    @Test
    public void updateReviewTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewManagerMock.updateReview(ArgumentMatchers.any(Review.class))).thenReturn(testReview);
	review_Controller.updateReview(testReview);
    }

    @Test
    public void deleteReviewTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewManagerMock.deleteReview(ArgumentMatchers.any(Long.class))).thenReturn(testReview);
	review_Controller.deleteReview(testReview.getReview_id().toString());
    }

    @Test
    public void updateReviewFormTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewManagerMock.deleteReview(ArgumentMatchers.any(Long.class))).thenReturn(testReview);
	review_Controller.updateForm(testReview.getReview_id().toString());
    }

    @Test
    public void fetchReviewTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewManagerMock.getReviewForUpdate(ArgumentMatchers.any(Long.class))).thenReturn(testReview);
	review_Controller.fetchReview(testReview.getReview_id().toString());
    }

}
