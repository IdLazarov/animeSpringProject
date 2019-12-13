package com.animeProject.animeProject.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity(name = "User")
@JsonIgnoreProperties("password")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "username",nullable = false,unique = true)
	private String username;
	private String password;
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	@OneToMany(mappedBy = "owner",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<FavouriteAnime> favouriteAnime;
	
	@OneToMany(mappedBy = "owner",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<FavouriteCharacter> favouriteCharacter;
	
	
	public User() {
		
	}
	
	
	public User(final String username, final String password, final String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(final String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(final String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(final String email) {
		this.email = email;
	}


	public List<FavouriteAnime> getFavouriteAnime() {
		return favouriteAnime;
	}


	public void setFavouriteAnime(List<FavouriteAnime> favouriteAnime) {
		this.favouriteAnime = favouriteAnime;
	}


	public List< FavouriteCharacter> getFavouriteCharacter() {
		return favouriteCharacter;
	}


	public void setFavouriteCharacter(List< FavouriteCharacter> favouriteCharacter) {
		this.favouriteCharacter = favouriteCharacter;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	public void addFavouriteAnime(FavouriteAnime favouriteAnimes) {
		getFavouriteAnime().add(favouriteAnimes);
		
	}
	
	public void addFavouriteCharacter(FavouriteCharacter favouriteCharacter) {
		getFavouriteCharacter().add(favouriteCharacter);
	}
	
	
	
	
}
