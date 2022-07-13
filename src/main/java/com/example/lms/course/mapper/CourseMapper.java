package com.example.lms.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lms.course.dto.CourseDto;
import com.example.lms.course.model.CourseParam;

@Mapper
public interface CourseMapper {
	long selectListCount(CourseParam courseParam);
	List<CourseDto> selectList(CourseParam courseParam);
}
