package com.MGL_Task4.controller;

import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MGL_Endpoint_Constants_Test {
    @BeforeMethod
    public void setup() {
	MockitoAnnotations.initMocks(this);
    }

    /**
     * URI endpoints for MGL_Endpoint_Constants
     */
    @Test
    public void testEndpoints() {
	@SuppressWarnings("unused")
	MGL_Endpoint_Constants mec = new MGL_Endpoint_Constants();
	Assertions.assertEquals(MGL_Endpoint_Constants.BLANK_ENDPOINT, "/MGL_Task4/");
	Assertions.assertEquals(MGL_Endpoint_Constants.HOME, "/MGL_Task4/home");
	Assertions.assertEquals(MGL_Endpoint_Constants.REVIEWS_FOR_GAME, "/MGL_Task4/reviewsForGame");
	Assertions.assertEquals(MGL_Endpoint_Constants.ADD_REVIEW, "/MGL_Task4/addReview");
	Assertions.assertEquals(MGL_Endpoint_Constants.GAMES, "/MGL_Task4/games");
	Assertions.assertEquals(MGL_Endpoint_Constants.FETCH_ALL_GAMES, "/MGL_Task4/fetchAllGames");
	Assertions.assertEquals(MGL_Endpoint_Constants.FETCH_REVIEWS_FOR_GAME, "/MGL_Task4/fetchReviewsForGame");
	Assertions.assertEquals(MGL_Endpoint_Constants.CREATE_GAME, "/MGL_Task4/createGame");
	Assertions.assertEquals(MGL_Endpoint_Constants.UPDATE_GAME, "/MGL_Task4/updateGame");
	Assertions.assertEquals(MGL_Endpoint_Constants.DELETE_GAME, "/MGL_Task4/deleteGame");
	Assertions.assertEquals(MGL_Endpoint_Constants.SHOW_FORM_FOR_ADD_REVIEW, "/MGL_Task4/review/showForm");
	Assertions.assertEquals(MGL_Endpoint_Constants.SAVE_REVIEW, "/MGL_Task4/review/addReview");
	Assertions.assertEquals(MGL_Endpoint_Constants.UPDATE_FORM, "/MGL_Task4/review/updateForm");
	Assertions.assertEquals(MGL_Endpoint_Constants.DELETE_REVIEW, "/MGL_Task4/review/deleteReview");
	Assertions.assertEquals(MGL_Endpoint_Constants.UPDATE_REVIEW, "/MGL_Task4/review/updateReview");
	Assertions.assertEquals(MGL_Endpoint_Constants.FETCH_REVIEW, "/MGL_Task4/review/fetchReview");
    }

}
