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
import com.example.lms.course.dto.TakeCourseDto;
import com.example.lms.course.model.CourseInput;
import com.example.lms.course.model.CourseParam;
import com.example.lms.course.model.ServiceResult;
import com.example.lms.course.model.TakeCourseParam;
import com.example.lms.course.service.CourseService;
import com.example.lms.course.service.TakeCourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController extends BaseController {

	private final TakeCourseService takeCourseSerivce;
	
	@GetMapping("/admin/takecourse/list.do")
	public String list(Model model, TakeCourseParam takeCourseParam) {
		
		takeCourseParam.init();
		List<TakeCourseDto> takeCourseList = takeCourseSerivce.list(takeCourseParam);
		
		long totalCount = 0;
		if (!CollectionUtils.isEmpty(takeCourseList)) {
			totalCount = takeCourseList.get(0).getTotalCount();
		}
		
		String queryString = takeCourseParam.getQueryString();
		String pageHtml  = getPaperHtml(totalCount, 
										takeCourseParam.getPageSize(), 
										takeCourseParam.getPageIndex(), 
										queryString);
		
		model.addAttribute("list", takeCourseList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pager", pageHtml);
		
		return "admin/takecourse/list";
	}
	
	@PostMapping("/admin/takecourse/status.do")
	public String status(Model model, TakeCourseParam takeCourseParam) {
		
		
		ServiceResult result = takeCourseSerivce.updateStatus
				(takeCourseParam.getId(), takeCourseParam.getStatus());
		if (!result.isResult()) {
			model.addAttribute("message", result.getMessage());
			return "common/error";
		}
		
		return "redirect:/admin/takecourse/list.do";
	}
}
