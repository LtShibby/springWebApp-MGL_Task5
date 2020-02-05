package com.MGL_Task4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long review_id;

    @Column(name = "review_body", nullable = false)
    private String review_body;

    @Column(name = "review_author")
    private String review_author;

    @Column(name = "review_rating")
    private Integer review_rating;

    @Column(name = "review_game_id")
    private Integer review_game_id;

    public Integer getReview_game_id() {
	return review_game_id;
    }

    public void setReview_game_id(Integer review_game_id) {
	this.review_game_id = review_game_id;
    }

    public Long getReview_id() {
	return review_id;
    }

    public void setReview_id(Long review_id) {
	this.review_id = review_id;
    }

    public String getReview_body() {
	return review_body;
    }

    public void setReview_body(String review_body) {
	this.review_body = review_body;
    }

    public String getReview_author() {
	return review_author;
    }

    public void setReview_author(String review_author) {
	if (review_author.equals("")) {
	    review_author = "anonymous";
	}
	this.review_author = review_author;
    }

    public Integer getReview_rating() {
	return review_rating;
    }

    public void setReview_rating(Integer review_rating) {
	this.review_rating = review_rating;
    }

    @Override
    public String toString() {
	return "Review [review_id=" + review_id + ", review_body=" + review_body + ", review_author=" + review_author
		+ ", review_rating=" + review_rating + ", review_game_id=" + review_game_id + "]";
    }

}
