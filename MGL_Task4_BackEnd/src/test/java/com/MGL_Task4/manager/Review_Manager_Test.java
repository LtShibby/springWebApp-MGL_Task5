package com.MGL_Task4.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.MGL_Task4.model.Review;
import com.MGL_Task4.service.Review_Service_Impl;

@RunWith(MockitoJUnitRunner.class)
public class Review_Manager_Test {

    @Mock
    Review_Service_Impl reviewServiceMock = Mockito.mock(Review_Service_Impl.class);

    @InjectMocks
    ReviewManagerImpl reviewManager;

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
	Mockito.when(reviewServiceMock.getReviews(ArgumentMatchers.anyInt())).thenReturn(reviewList);
	Assertions.assertEquals(reviewList,
		reviewManager.getReviews(Long.valueOf(testReview.getReview_game_id().toString())));
    }

    @Test
    public void createReviewTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewServiceMock.saveReview(ArgumentMatchers.any(Review.class))).thenReturn(testReview);
	Assertions.assertEquals(testReview, reviewManager.saveReview(testReview));
    }

    @Test
    public void updateReviewTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewServiceMock.updateReview(ArgumentMatchers.any(Review.class))).thenReturn(testReview);
	Assertions.assertEquals(testReview, reviewManager.updateReview(testReview));
    }

    @Test
    public void deleteReviewTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewServiceMock.deleteReview(ArgumentMatchers.any(Long.class))).thenReturn(testReview);
	Assertions.assertEquals(testReview, reviewManager.deleteReview(testReview.getReview_id()));
    }

    @Test
    public void getReviewTest() {
	Review testReview = setReviewObjectForTest();
	Optional<Review> optionalReview = null;
	Mockito.when(reviewServiceMock.getReview(ArgumentMatchers.any(Long.class))).thenReturn(optionalReview);
	Assertions.assertEquals(optionalReview, reviewManager.getReview(testReview.getReview_id()));
    }

    @Test
    public void getReviewForUpdateTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewServiceMock.getReviewForUpdate(ArgumentMatchers.any(Long.class))).thenReturn(testReview);
	Assertions.assertEquals(testReview, reviewManager.getReviewForUpdate(testReview.getReview_id()));
    }
}
