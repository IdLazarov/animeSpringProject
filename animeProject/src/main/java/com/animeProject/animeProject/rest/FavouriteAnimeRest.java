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

import com.animeProject.animeProject.models.FavouriteAnime;

import com.animeProject.animeProject.models.User;
import com.animeProject.animeProject.repos.FavouriteAnimeRepository;
import com.animeProject.animeProject.repos.UserRepository;

@RestController
public class FavouriteAnimeRest {

	private FavouriteAnimeRepository favAnimeRepo;
	private UserRepository userRepo;
	
	@Autowired
	public FavouriteAnimeRest(FavouriteAnimeRepository favAnimeRepo, UserRepository userRepo) {
		this.favAnimeRepo = favAnimeRepo;
		this.userRepo = userRepo;
	}
	
	
	
	@PostMapping("/addFavouriteAnime")
	public ResponseEntity<FavouriteAnime> addFavouriteAnime(
		
		@RequestParam(name = "title") String  title,
		@RequestParam(name = "startYear") String startYear,
		@RequestParam(name = "author") String  author,
		@RequestParam(name = "favouriteCharacter") String  favouriteCharacter,
		HttpSession session){
		
			final User user = (User) session.getAttribute("currentUser");
			if (null == user) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
	final FavouriteAnime favouriteAnime = new FavouriteAnime(title,startYear,author,favouriteCharacter);
	favouriteAnime.setOwner(user);
	user.addFavouriteAnime(favouriteAnime);
	
	session.setAttribute("currentuser", userRepo.save(user));
	
	return ResponseEntity.ok(favouriteAnime);
	}
	
	
	
	
	@GetMapping("/getFavouriteAnime")
	public ResponseEntity<List<FavouriteAnime>> getAllPosts(HttpSession session) {
		final List<FavouriteAnime> favouriteAnime = new ArrayList<>();
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(favouriteAnime);
		} else {
			favouriteAnime.addAll(favAnimeRepo.findByTitle(user));
		}
		return ResponseEntity.ok(favouriteAnime);
	}
	
	
	
	
	@PostMapping("/removeMyFavouriteAnime")
	public ResponseEntity<String> removeFavouriteAnime(@RequestParam(name = "id") int id, HttpSession session) {
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
													.body("");
		}
		final FavouriteAnime favouriteAnimeForRemove = favAnimeRepo.findById(id).orElse(null);
		if (null != favouriteAnimeForRemove) {
			if (!user.equals(favouriteAnimeForRemove.getOwner())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			} else {
				favAnimeRepo.delete(favouriteAnimeForRemove);
			}
		}
		return ResponseEntity.ok().body("Anime with id: " + id 
										+ " is removed from favourites");
	}
	
	
}
