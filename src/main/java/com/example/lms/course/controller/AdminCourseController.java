package com.example.lms.course.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.lms.course.dto.CourseDto;
import com.example.lms.course.model.CourseInput;
import com.example.lms.course.model.CourseParam;
import com.example.lms.course.service.CourseService;
import com.example.lms.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminCourseController extends BaseController {

	private final CourseService courseSerivce;
	
	@GetMapping("/admin/course/list.do")
	public String list(Model model, CourseParam courseParam) {
		
		courseParam.init();
		List<CourseDto> courseList = courseSerivce.list(courseParam);
		
		long totalCount = 0;
		if (!CollectionUtils.isEmpty(courseList)) {
			totalCount = courseList.get(0).getTotalCount();
		}
		
		String queryString = courseParam.getQueryString();
		String pageHtml  = getPaperHtml(totalCount, 
										courseParam.getPageSize(), 
										courseParam.getPageIndex(), 
										queryString);
		
		model.addAttribute("list", courseList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pager", pageHtml);
		
		return "admin/course/list";
	}
	
	@GetMapping("/admin/course/add.do")
	public String add(Model model) {
		
		return "admin/course/add";
	}
	
	@PostMapping("/admin/course/add.do")
	public String addSubmit(Model model, CourseInput courseInput) {
		
		boolean result = courseSerivce.add(courseInput);
		
		return "redirect:/admin/course/list.do";
	}
}
