package com.example.lms.admin.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.lms.admin.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryDto {
	
	Long id;
	String categoryName;
	int sortValue;
	boolean usingYn;
	
	int courseCount;
	
	public static List<CategoryDto> of(List<Category> categories) {
		if (categories != null) {
			List<CategoryDto> categoryDtoList = new ArrayList<>();
			for (Category x : categories) {
				categoryDtoList.add(of(x));
			}
			return categoryDtoList;
		}
		
		return null;
	}
	
	public static CategoryDto of(Category category) {
		return CategoryDto.builder()
						.id(category.getId())
						.categoryName(category.getCategoryName())
						.sortValue(category.getSortValue())
						.usingYn(category.isUsingYn())
						.build();
	}
}
