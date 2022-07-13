package com.example.lms.course.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.lms.course.domain.Course;
import com.example.lms.course.dto.CourseDto;
import com.example.lms.course.mapper.CourseMapper;
import com.example.lms.course.model.CourseInput;
import com.example.lms.course.model.CourseParam;
import com.example.lms.course.repository.CourseRepository;
import com.example.lms.course.service.CourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;
	
	@Override
	public boolean add(CourseInput courseInput) {
			
		Course course = Course.builder()
							.subject(courseInput.getSubject())
							.createAt(LocalDateTime.now())
							.build();
		courseRepository.save(course);
		
		return true;
	}

	@Override
	public List<CourseDto> list(CourseParam courseParam) {

		long totalCount = courseMapper.selectListCount(courseParam);
		List<CourseDto> list = courseMapper.selectList(courseParam);
		if (!CollectionUtils.isEmpty(list)) {
			int i = 0;
			for(CourseDto x: list) {
				x.setTotalCount(totalCount);
				x.setSeq(totalCount - courseParam.getPageStart() - i);
				i++;
			}
		}
		return list;
	}

}
