package com.animeProject.animeProject.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FavouriteAnime implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String startYear;
	private String author;
	
	private String favouriteCharacter;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private User owner;
	
	



	public FavouriteAnime() {
		
	}
	
	
	public FavouriteAnime(String title,String startYear,String author,String favouriteCharacter) {
		
		this.title = title;
		this.startYear = startYear;
		this.author = author;
		this.favouriteCharacter = favouriteCharacter;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getStartYear() {
		return startYear;
	}


	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}


	
	
	public String getFavouriteCharacter() {
		return favouriteCharacter;
	}


	public void setFavouriteCharacter(String favouriteCharacter) {
		this.favouriteCharacter = favouriteCharacter;
	}

	

	
	
}
