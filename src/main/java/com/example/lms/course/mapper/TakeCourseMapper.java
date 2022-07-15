package com.example.lms.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lms.course.dto.TakeCourseDto;
import com.example.lms.course.model.TakeCourseParam;

@Mapper
public interface TakeCourseMapper {
	long selectListCount(TakeCourseParam takeCourseParam);
	List<TakeCourseDto> selectList(TakeCourseParam takeCourseParam);
}
