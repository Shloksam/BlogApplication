package com.backendAPI.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty
	@Size(min=4, message="Minimum Size of Category is 4")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min=4, message="Minimum Size of Category is 4")
	private String categoryDescription; 

}
