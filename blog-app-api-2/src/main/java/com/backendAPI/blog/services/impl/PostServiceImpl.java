package com.backendAPI.blog.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendAPI.blog.entities.Category;
import com.backendAPI.blog.entities.Post;
import com.backendAPI.blog.entities.User;
import com.backendAPI.blog.exceptions.ResourceNotFoundException;
import com.backendAPI.blog.payloads.CategoryDto;
import com.backendAPI.blog.payloads.PostDto;
import com.backendAPI.blog.repositories.CategoryRepo;
import com.backendAPI.blog.repositories.PostRepo;
import com.backendAPI.blog.repositories.UserRepo;
import com.backendAPI.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Category cat = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", catId));

		Post post = this.dtoToPost(postDto);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(cat);
		post.setUser(user);
		Post newPost = postRepo.save(post);
		
		return this.postToDto(newPost);	
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
	
		post.setPost_Content(postDto.getPost_Content());
		post.setPostTitle(postDto.getPostTitle());
		
		Post updatedPost = this.postRepo.save(post);
	    return this.postToDto(updatedPost);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));		
	    this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts = this.postRepo.findAll();
		List<PostDto> postDto = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
		return postDto;
	}
	
	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));		
		return  this.postToDto(post);
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));	
		List<Post> postList = this.postRepo.findByUser(user);
		List<PostDto> posts = postList.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
		return posts;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postsDto = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Post dtoToPost(PostDto postDto) {
		Post post = this.modelMapper.map(postDto, Post.class);
		return post;
	}

	public PostDto postToDto(Post post) {
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

}
