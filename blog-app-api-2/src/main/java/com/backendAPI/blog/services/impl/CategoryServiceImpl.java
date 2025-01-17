package com.backendAPI.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendAPI.blog.entities.Category;
import com.backendAPI.blog.exceptions.ResourceNotFoundException;
import com.backendAPI.blog.payloads.CategoryDto;
import com.backendAPI.blog.repositories.CategoryRepo;
import com.backendAPI.blog.services.CategoryService;

import jakarta.validation.Valid;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(@Valid CategoryDto categorydto) {
		Category category = this.dtoToCategory(categorydto);
		Category savedcategory = categoryRepo.save(category);
		return this.categoryToDto(savedcategory);
	}

	@Override
	public CategoryDto updateCategory(@Valid CategoryDto categoryDto,Integer categoryId) {
		Category newcategory = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		newcategory.setCategoryDescription(categoryDto.getCategoryDescription());
		newcategory.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category saveCategory = this.categoryRepo.save(newcategory);
		return this.categoryToDto(saveCategory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        return this.categoryToDto(cat);
	}

	@Override
	public List<CategoryDto> getAllCategories() {                     
		List<Category> categories = this.categoryRepo.findAll();            
		List<CategoryDto> categoriesDto = categories.stream().map(category -> this.categoryToDto(category)).collect(Collectors.toList());
		return categoriesDto;
	}
	
	public Category dtoToCategory(CategoryDto categorydto) {
		return this.modelMapper.map(categorydto, Category.class);
	}
	
	public CategoryDto categoryToDto(Category category) {
		return this.modelMapper.map(category, CategoryDto.class);
	}
	
	

}
