package com.example.lms.course.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.admin.service.CategoryService;
import com.example.lms.common.model.ResponseResult;
import com.example.lms.course.model.ServiceResult;
import com.example.lms.course.model.TakeCourseInput;
import com.example.lms.course.service.CourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiCourseController extends BaseController {

	private final CourseService courseService;
	private final CategoryService categoryService;
	
	@PostMapping("/api/course/req.api")
	public ResponseEntity<?> courseReq(Model model, 
			@RequestBody TakeCourseInput takeCourseInput, 
			Principal principal) 
	{
		takeCourseInput.setUserId(principal.getName());
		
		ServiceResult result = courseService.req(takeCourseInput);
		
		if (!result.isResult()) {
			
			ResponseResult responseResult = new ResponseResult(false, result.getMessage());
			return ResponseEntity.ok().body(responseResult);
		}
		
		ResponseResult responseResult = new ResponseResult(true);
		return ResponseEntity.ok().body(responseResult);
	}	
}
