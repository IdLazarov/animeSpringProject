package com.animeProject.animeProject.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyFavouritesControler {
	@GetMapping("/myFavourites")
	public String redirectToMyFavourite() {
		return "myFavourites";
	}
}
