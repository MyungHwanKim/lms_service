package com.example.lms.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.lms.admin.domain.Category;
import com.example.lms.admin.dto.CategoryDto;
import com.example.lms.admin.repository.CategoryRepository;
import com.example.lms.admin.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDto> list() {		
		List<Category> categories = categoryRepository.findAll();
		return CategoryDto.of(categories);
	}

	@Override
	public boolean add(String categoryName) {
		
		Category category = Category.builder()
									.categoryName(categoryName)
									.usingYn(true)
									.sortValue(0)
									.build();
		
		categoryRepository.save(category);
		
		return false;
	}

	@Override
	public boolean update(CategoryDto categoryDto) {

		return false;
	}

	@Override
	public boolean del(long id) {

		return false;
	}
}
