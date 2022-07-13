package com.example.lms.course.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.lms.admin.service.CategoryService;
import com.example.lms.course.dto.CourseDto;
import com.example.lms.course.model.CourseInput;
import com.example.lms.course.model.CourseParam;
import com.example.lms.course.service.CourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminCourseController extends BaseController {

	private final CourseService courseSerivce;
	private final CategoryService categoryService;
	
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
	
	@GetMapping({"/admin/course/add.do", "/admin/course/edit.do"})
	public String add(Model model, 
						HttpServletRequest request, 
						CourseInput courseInput) 
	{
		
		model.addAttribute("category", categoryService.list());
		
		boolean editMode = request.getRequestURI().contains("/edit.do");
		CourseDto detail = new CourseDto();
		
		if (editMode) {
			long id = courseInput.getId();
			
			CourseDto existCourse = courseSerivce.getById(id);
			
			if (existCourse == null) {
				model.addAttribute("message", "강좌 정보가 존재하지 않습니다.");
				return "common/error";
			}
			detail = existCourse;
		}
		
		model.addAttribute("editMode", editMode);
		model.addAttribute("detail", detail);
		return "admin/course/add";
	}
	
	@PostMapping({"/admin/course/add.do", "/admin/course/edit.do"})
	public String addSubmit(Model model, 
							HttpServletRequest request, 
							CourseInput courseInput) 
	{
		
		boolean editMode = request.getRequestURI().contains("/edit.do");
		CourseDto detail = new CourseDto();
		
		if (editMode) {
			long id = courseInput.getId();
			
			CourseDto existCourse = courseSerivce.getById(id);
			
			if (existCourse == null) {
				model.addAttribute("message", "강좌 정보가 존재하지 않습니다.");
				return "common/error";
			}
			boolean result = courseSerivce.set(courseInput);
		} else {
			boolean result = courseSerivce.add(courseInput);
		}
		
		return "redirect:/admin/course/list.do";
	}
}
