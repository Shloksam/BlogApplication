package com.backendAPI.blog.payloads;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApiResponse {
	
	private String message;
	private boolean success;
	

}
