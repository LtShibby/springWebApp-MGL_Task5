package com.MGL_Task4.controller;

import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MGL_Task4_Controller_Test {
    @BeforeMethod
    public void setup() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void homeEndpointTest() {
	MGL_Task4_Controller mgl_Task4_Controller = new MGL_Task4_Controller();
	Assertions.assertEquals("home", mgl_Task4_Controller.home());
    }
}
