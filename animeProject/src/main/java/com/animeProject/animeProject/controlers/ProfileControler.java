package com.animeProject.animeProject.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileControler {

	@GetMapping("/profile")
	public String redirectToIndex() {
		return "profile";
	}
	
}