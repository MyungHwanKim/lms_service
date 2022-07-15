package com.example.lms.member.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.common.model.ResponseResult;
import com.example.lms.course.dto.TakeCourseDto;
import com.example.lms.course.model.ServiceResult;
import com.example.lms.course.model.TakeCourseInput;
import com.example.lms.course.service.TakeCourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiMemberController {

	private final TakeCourseService takeCourseService;
	
	@PostMapping("/api/member/course/cancel.api")
	public ResponseEntity<?> cancelCourse(Model model
			, @RequestBody TakeCourseInput takeCourseInput
			, Principal principal) {
		
		String userId = principal.getName();
		
		TakeCourseDto detail = takeCourseService.detail(takeCourseInput.getTakeCourseId());
		if (detail == null) {
			ResponseResult responseResult = new ResponseResult(false, "수강 신청 정보가 존재하지 않습니다.");
			return ResponseEntity.ok().body(responseResult);
		}
		
		if (userId == null || !userId.equals(detail.getUserId())) {
			ResponseResult responseResult = new ResponseResult(false, "본인의 수강 신청 정보만 취소할 수 있습니다.");
			return ResponseEntity.ok().body(responseResult);
		}
	
		ServiceResult result = takeCourseService.cancel(takeCourseInput.getTakeCourseId());
		if (!result.isResult()) {
			ResponseResult responseResult = new ResponseResult(false, result.getMessage());
			return ResponseEntity.ok().body(responseResult);
		}
		ResponseResult responseResult = new ResponseResult(true);
		return ResponseEntity.ok().body(responseResult);
	}
}
