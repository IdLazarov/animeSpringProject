package com.animeProject.animeProject.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeProject.animeProject.models.FavouriteCharacter;

import com.animeProject.animeProject.models.User;

public interface FavouriteCharacterRepository extends JpaRepository<FavouriteCharacter, Integer>{

	String findByName(String name);

	public List<FavouriteCharacter> findByName(final User owner);
}
