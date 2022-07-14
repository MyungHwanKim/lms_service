package com.example.lms.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lms.admin.dto.CategoryDto;
import com.example.lms.admin.dto.MemberDto;
import com.example.lms.admin.model.MemberParam;

@Mapper
public interface CategoryMapper {

	List<CategoryDto> select(CategoryDto categoryDto);
}
