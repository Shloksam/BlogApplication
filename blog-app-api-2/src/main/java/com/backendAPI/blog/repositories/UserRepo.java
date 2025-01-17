package com.backendAPI.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendAPI.blog.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	
}
 