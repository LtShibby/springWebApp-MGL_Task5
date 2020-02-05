package com.MGL_Task4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.MGL_Task4.model.Review;
import com.MGL_Task4.repository.ReviewRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class Review_Service_Test {

    @Mock
    ReviewRepository reviewRepositoryMock = Mockito.mock(ReviewRepository.class);

    @InjectMocks
    Review_Service_Impl reviewService;

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
	testReview.setReview_author("");
	testReview.getReview_author();
	testReview.getReview_body();
	testReview.getReview_game_id();
	testReview.getReview_id();
	testReview.getReview_rating();
	testReview.toString();
	testReview2.setReview_id(Long.valueOf(2));
	List<Review> reviewList = new ArrayList<>();
	reviewList.add(testReview);
	reviewList.add(testReview2);
	Mockito.when(reviewRepositoryMock.findByReviewGameId(testReview.getReview_game_id())).thenReturn(reviewList);
	Assert.assertEquals(reviewList, reviewService.getReviews(testReview.getReview_game_id()));
    }

    @Test
    public void createReviewTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewRepositoryMock.save(ArgumentMatchers.any(Review.class))).thenReturn(testReview);
	Assert.assertEquals(testReview, reviewService.saveReview(testReview));
    }

    @Test
    public void updateReviewTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewRepositoryMock.save(ArgumentMatchers.any(Review.class))).thenReturn(testReview);
	Assert.assertEquals(testReview, reviewService.updateReview(testReview));
    }

    @Test
    public void deleteReviewTest() {
	Review testReview = setReviewObjectForTest();
	reviewService.deleteReview(testReview.getReview_id());
    }

    @Test
    public void getReviewTest() {
	Review testReview = setReviewObjectForTest();
	Optional<Review> optionalReview = null;
	Mockito.when(reviewRepositoryMock.findById(ArgumentMatchers.any(Long.class))).thenReturn(optionalReview);
	Assert.assertEquals(optionalReview, reviewService.getReview(testReview.getReview_id()));
    }

    @Test
    public void getReviewForUpdateTest() {
	Review testReview = setReviewObjectForTest();
	Mockito.when(reviewRepositoryMock.getOne(ArgumentMatchers.any(Long.class))).thenReturn(testReview);
	Assert.assertEquals(testReview, reviewService.getReviewForUpdate(testReview.getReview_id()));
    }
}
