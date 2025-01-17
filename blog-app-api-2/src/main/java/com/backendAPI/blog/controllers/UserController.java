package com.backendAPI.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backendAPI.blog.payloads.ApiResponse;
import com.backendAPI.blog.payloads.UserDto;
import com.backendAPI.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController { 
    
    @Autowired
    private UserService userService;
    
    //POST - Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    
    // PUT - Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto, @PathVariable("userId") Integer uid){
    	UserDto updatedUser = this.userService.updateUser(userdto,uid);
        return  ResponseEntity.ok(updatedUser);
    }
    
    //DELETE - User Deleted
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
    	this.userService.deleteUser(uid);
    	return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }
    
    // GET - get All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
    	return ResponseEntity.ok(this.userService.getAllUsers());
    }
    
    //GET - get User by Id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uid) {
        UserDto user = this.userService.getuserById(uid);
        return  ResponseEntity.ok(user);
    }
}
