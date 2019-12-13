package com.animeProject.animeProject.rest;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animeProject.animeProject.models.User;



@RestController
public class UserInfoRest {

	@GetMapping("/getCurrentUser")
	public User getCurrentUser(final HttpSession session) {
		return (User) session.getAttribute("currentUser");
	}
}