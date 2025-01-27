package com.backendAPI.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendAPI.blog.entities.Category;
import com.backendAPI.blog.entities.Post;
import com.backendAPI.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}
