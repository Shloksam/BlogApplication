package com.backendAPI.blog.payloads;

import java.util.Date;

import com.backendAPI.blog.entities.Category;
import com.backendAPI.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter

public class PostDto {
	
	private String postTitle;
	
	private String post_Content; 
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;

}
