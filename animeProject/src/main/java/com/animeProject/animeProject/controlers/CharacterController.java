package com.animeProject.animeProject.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CharacterController {

	@GetMapping("/character")
	public String redirectToCharacter() {
		return "character";
	}
}
