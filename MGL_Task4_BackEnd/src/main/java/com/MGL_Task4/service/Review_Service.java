package com.MGL_Task4.service;

import java.util.List;
import java.util.Optional;

import com.MGL_Task4.model.Review;

public interface Review_Service {

    Review saveReview(Review review);

    Review updateReview(Review review);

    Optional<Review> getReview(Long id);

    List<Review> getReviews(Integer review_game_id);

    Review deleteReview(Long id);

    Review getReviewForUpdate(Long review_game_id);

}
