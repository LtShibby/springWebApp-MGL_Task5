package com.MGL_Task4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MGL_Task4_Controller {

    @RequestMapping(value = { MGL_Endpoint_Constants.BLANK_ENDPOINT,
	    MGL_Endpoint_Constants.HOME }, method = RequestMethod.GET)
    public String home() {
	return "home";
    }
}
