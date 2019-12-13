package com.animeProject.animeProject.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnimeControler {

	@GetMapping("/anime")
	public String redirectToIndex() {
		return "anime";
	}
	
}
