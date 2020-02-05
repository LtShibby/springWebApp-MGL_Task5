package com.MGL_Task4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MGL_Task4.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("from Review r where r.review_game_id = :review_game_id")
    List<Review> findByReviewGameId(@Param("review_game_id") Integer review_game_id);
}
