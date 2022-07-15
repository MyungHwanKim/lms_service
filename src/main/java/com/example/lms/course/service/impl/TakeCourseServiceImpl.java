package com.example.lms.course.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.lms.course.domain.TakeCourse;
import com.example.lms.course.domain.TakeCourseCode;
import com.example.lms.course.dto.TakeCourseDto;
import com.example.lms.course.mapper.TakeCourseMapper;
import com.example.lms.course.model.ServiceResult;
import com.example.lms.course.model.TakeCourseParam;
import com.example.lms.course.repository.TakeCourseRepository;
import com.example.lms.course.service.TakeCourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TakeCourseServiceImpl implements TakeCourseService {

	private final TakeCourseRepository takeCourseRepository;
	private final TakeCourseMapper takecourseMapper;
	
	@Override
	public List<TakeCourseDto> list(TakeCourseParam takeCourseParam) {

		long totalCount = takecourseMapper.selectListCount(takeCourseParam);
		
		List<TakeCourseDto> list = takecourseMapper.selectList(takeCourseParam);
		if (!CollectionUtils.isEmpty(list)) {
			int i = 0;
			for(TakeCourseDto x: list) {
				x.setTotalCount(totalCount);
				x.setSeq(totalCount - takeCourseParam.getPageStart() - i);
				i++;
			}
		}
		return list;
	}
	
	@Override
	public TakeCourseDto detail(long id) {

		Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
		if (optionalTakeCourse.isPresent()) {
			return TakeCourseDto.of(optionalTakeCourse.get());
		}
		
		return null;
	}

	@Override
	public ServiceResult cancel(long id) {
		Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
		if (!optionalTakeCourse.isPresent()) {
			return new ServiceResult(false, "수강 정보가 존재하지 않습니다.");
		}
		
		TakeCourse takeCourse = optionalTakeCourse.get();
		
		takeCourse.setStatus(TakeCourseCode.STATUS_CANCEL);
		takeCourseRepository.save(takeCourse);
		
		return new ServiceResult(true);
	}

	@Override
	public ServiceResult updateStatus(long id, String status) {
		
		Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
		if (!optionalTakeCourse.isPresent()) {
			return new ServiceResult(false, "수강 정보가 존재하지 않습니다.");
		}
		
		TakeCourse takeCourse = optionalTakeCourse.get();
		
		takeCourse.setStatus(status);
		takeCourseRepository.save(takeCourse);
		
		return new ServiceResult(true);
	}

	@Override
	public List<TakeCourseDto> myCourse(String userId) {

		TakeCourseParam takeCourseParam = new TakeCourseParam();
		takeCourseParam.setUserId(userId);
		List<TakeCourseDto> list =takecourseMapper.selectListMyCourse(takeCourseParam);
		
		return list;
	}
}
