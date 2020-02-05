package com.MGL_Task4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MGL_Task4.manager.ReviewManager;
import com.MGL_Task4.model.Review;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class Review_Controller {

    @Autowired
    private ReviewManager reviewManager;

    @RequestMapping(value = MGL_Endpoint_Constants.UPDATE_FORM, method = RequestMethod.GET)
    public Review updateForm(@RequestParam("review_id") String review_id) {
	Review review = reviewManager.getReviewForUpdate(Long.valueOf(review_id));
	return review;
    }

    @RequestMapping(value = MGL_Endpoint_Constants.DELETE_REVIEW, method = RequestMethod.GET)
    public ResponseEntity<?> deleteReview(@RequestParam("review_id") String review_id) {
	Optional<Review> reviewToDelete = reviewManager.getReview(Long.valueOf(review_id));
	reviewManager.deleteReview(Long.valueOf(review_id));
	return new ResponseEntity<>(reviewToDelete, HttpStatus.OK);
    }

    @RequestMapping(value = MGL_Endpoint_Constants.UPDATE_REVIEW, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateReview(@RequestBody Review review) {
	return new ResponseEntity<>(reviewManager.saveReview(review), HttpStatus.OK);
    }

    @RequestMapping(value = MGL_Endpoint_Constants.ADD_REVIEW, method = RequestMethod.POST)
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
	return new ResponseEntity<>(reviewManager.saveReview(review), HttpStatus.OK);
    }

    @RequestMapping(value = MGL_Endpoint_Constants.FETCH_REVIEWS_FOR_GAME, method = RequestMethod.PUT)
    public ResponseEntity<List<Review>> fetchReviewsForGame(@RequestBody String review_game_id) {
	return new ResponseEntity<>(reviewManager.getReviews(Long.valueOf(review_game_id)), HttpStatus.OK);
    }

    @RequestMapping(value = MGL_Endpoint_Constants.FETCH_REVIEW, method = RequestMethod.GET)
    public ResponseEntity<Review> fetchReview(@RequestParam String review_id) {
	return new ResponseEntity<>(reviewManager.getReviewForUpdate(Long.valueOf(review_id)), HttpStatus.OK);
    }

}
