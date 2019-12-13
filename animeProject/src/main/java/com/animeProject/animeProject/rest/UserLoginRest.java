package com.animeProject.animeProject.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.animeProject.animeProject.models.User;
import com.animeProject.animeProject.repos.UserRepository;



@RestController
public class UserLoginRest {

	private UserRepository repository;
	

	@Autowired
	public UserLoginRest(final UserRepository repository) {
		this.repository = repository;
		
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
			HttpSession session) {
		final User currentUser = repository
							.findByUsernameAndPassword(username, password);
		if (null != currentUser) {
			session.setAttribute("currentUser", currentUser);
		}
		return "profile.html";
	}

	@PostMapping(value = "/register")
	public User register(@RequestParam(name = "email") String email, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		final User newUser = new User(username, password, email);
		return repository.saveAndFlush(newUser);
	}

}
