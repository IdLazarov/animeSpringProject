package com.animeProject.animeProject.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.animeProject.animeProject.models.FavouriteCharacter;
import com.animeProject.animeProject.models.User;

import com.animeProject.animeProject.repos.FavouriteCharacterRepository;
import com.animeProject.animeProject.repos.UserRepository;

@RestController
public class FavouriteCharacterRest {
	private FavouriteCharacterRepository favCharacterRepo;
	private UserRepository userRepo;
	
	@Autowired
	public FavouriteCharacterRest(FavouriteCharacterRepository favCharacterRepo, UserRepository userRepo) {
		this.favCharacterRepo = favCharacterRepo;
		this.userRepo = userRepo;
	}
	

	
	@PostMapping("/addFavouriteCharacter")
	public ResponseEntity<FavouriteCharacter> addFavouriteCharacter(
		
		@RequestParam(name = "name") String  name,
		@RequestParam(name = "age") String age,
		@RequestParam(name = "anime") String  anime,
		@RequestParam(name = "description") String description,
		HttpSession session){
		
			final User user = (User) session.getAttribute("currentUser");
			if (null == user) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
	final FavouriteCharacter favouriteCharacter = new FavouriteCharacter(name,age,anime,description);
	favouriteCharacter.setOwner(user);
	user.addFavouriteCharacter(favouriteCharacter);
	
	session.setAttribute("currentuser", favCharacterRepo.save(favouriteCharacter));
	
	return ResponseEntity.ok(favouriteCharacter);
	}
	
	
	
	
	@GetMapping("/getFavouriteCharacter")
	public ResponseEntity<List<FavouriteCharacter>> getAllPosts(HttpSession session) {
		final List<FavouriteCharacter> favouriteCharacter = new ArrayList<>();
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(favouriteCharacter);
		} else {
			favouriteCharacter.addAll(favCharacterRepo.findByName(user));
		}
		return ResponseEntity.ok(favouriteCharacter);
	}
	
	
	
	
	@PostMapping("/removeMyFavouriteCharacter")
	public ResponseEntity<String> removeFavouriteCharacter(@RequestParam(name = "id") int id, HttpSession session) {
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
													.body("");
		}
		final FavouriteCharacter favouriteCharacterForRemove = favCharacterRepo.findById(id).orElse(null);
		if (null != favouriteCharacterForRemove) {
			if (!user.equals(favouriteCharacterForRemove.getOwner())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			} else {
				favCharacterRepo.delete(favouriteCharacterForRemove);
			}
		}
		return ResponseEntity.ok().body("Anime with id: " + id 
										+ " is removed from favourites");
	}
	
}
