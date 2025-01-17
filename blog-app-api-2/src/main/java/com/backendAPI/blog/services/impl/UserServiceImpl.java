package com.backendAPI.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendAPI.blog.entities.User;
import com.backendAPI.blog.exceptions.ResourceNotFoundException;
import com.backendAPI.blog.payloads.UserDto;
import com.backendAPI.blog.repositories.UserRepo;
import com.backendAPI.blog.services.UserService;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(@Valid UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(@Valid UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getuserById(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		UserDto userDto1 = this.userToDto(user);
		return userDto1;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtolist = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtolist;
	}

	@Override
	public void deleteUser(Integer userId) {
	    User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
	    this.userRepo.delete(user);
	}

	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//	    UserDto userDto = new UserDto();
//	    userDto.setId(user.getId());
//	    userDto.setName(user.getName());
//	    userDto.setEmail(user.getEmail());
//	    userDto.setPassword(user.getPassword());
//	    userDto.setAbout(user.getAbout());
	    return userDto;
	}

}