package com.backendAPI.blog.services;

import java.util.List;

import com.backendAPI.blog.entities.Post;
import com.backendAPI.blog.payloads.PostDto;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto, Integer userId, Integer catId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all post
	List<PostDto> getAllPost();
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all post by User
	List<PostDto> getAllPostByUser(Integer userId);
	
	//get all post by Category
	List<PostDto> getAllPostByCategory( Integer categoryId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);

}
