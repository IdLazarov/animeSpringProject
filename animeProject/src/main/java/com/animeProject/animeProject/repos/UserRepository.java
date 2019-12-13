package com.animeProject.animeProject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animeProject.animeProject.models.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsernameAndPassword(final String username, final String password);

	User findByEmail(String email);
	
	User findByEmailAndPassword(final String email, final String password);

	
}
