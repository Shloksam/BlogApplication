package com.backendAPI.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter

public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4, message="Username must be minimum of 4 characters")
	private String name;
	
	@Email(message="Email address is not valid!")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10, message="Password is not valid")
	private String password;
	
	private String about;
	

}
