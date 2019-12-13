package com.animeProject.animeProject.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeProject.animeProject.models.FavouriteAnime;
import com.animeProject.animeProject.models.User;

public interface FavouriteAnimeRepository extends JpaRepository<FavouriteAnime, Integer> {

	FavouriteAnime findByTitle(String title);

	public List<FavouriteAnime> findByTitle(final User owner);
	
}
