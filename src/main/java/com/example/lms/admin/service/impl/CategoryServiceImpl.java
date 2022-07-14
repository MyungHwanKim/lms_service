package com.example.lms.admin.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.lms.admin.domain.Category;
import com.example.lms.admin.dto.CategoryDto;
import com.example.lms.admin.mapper.CategoryMapper;
import com.example.lms.admin.model.CategoryInput;
import com.example.lms.admin.repository.CategoryRepository;
import com.example.lms.admin.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	
	private final CategoryMapper categoryMapper;
	
	private Sort getSortBySortValueDesc() {
		return Sort.by(Sort.Direction.DESC, "sortValue");
	}
	
	@Override
	public List<CategoryDto> list() {		
		List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());
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
	public boolean update(CategoryInput categoryInput) {

		Optional<Category> optionalCategory = categoryRepository.findById(categoryInput.getId());
		
		if (optionalCategory.isPresent()) {
			
			Category category = optionalCategory.get();
			
			category.setCategoryName(categoryInput.getCategoryName());
			category.setSortValue(categoryInput.getSortValue());
			category.setUsingYn(categoryInput.isUsingYn());
			categoryRepository.save(category);
		}
		
		return true;
	}

	@Override
	public boolean del(long id) {		
		categoryRepository.deleteById(id);
		return true;
	}

	@Override
	public List<CategoryDto> frontList(CategoryDto categoryDto) {
		
		return categoryMapper.select(categoryDto);
	}
}
