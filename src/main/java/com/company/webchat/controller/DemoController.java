package com.company.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	public String showHome() {

		return "fancy-login";
	}
	
	// add request mapping for /leaders

	
}










