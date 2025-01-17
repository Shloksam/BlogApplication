package com.backendAPI.blog.services;

import java.util.List;

import com.backendAPI.blog.payloads.CategoryDto;

public interface CategoryService {
	
		CategoryDto createCategory(CategoryDto category);
		
		CategoryDto updateCategory(CategoryDto category, Integer categoryId);
		
		void deleteCategory(Integer categoryId);
		
		CategoryDto getCategory(Integer categoryId);
		
		List<CategoryDto> getAllCategories();

}
