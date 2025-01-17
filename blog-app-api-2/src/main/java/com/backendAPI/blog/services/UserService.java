package com.backendAPI.blog.services;

import java.util.List;

import com.backendAPI.blog.entities.User;
import com.backendAPI.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getuserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId); 

}
