package com.example.lms.course.service;

import java.util.List;

import com.example.lms.course.dto.CourseDto;
import com.example.lms.course.model.CourseInput;
import com.example.lms.course.model.CourseParam;

public interface CourseService {

	/*
	 * 강좌 등록
	 */
	boolean add(CourseInput courseInput);

	/*
	 * 강좌 정보 수정
	 */
	boolean set(CourseInput courseInput);
	
	/*
	 * 강좌 목록
	 */
	List<CourseDto> list(CourseParam courseParam);

	/*
	 * 강좌 상세 정보
	 */
	CourseDto getById(long id);
}
