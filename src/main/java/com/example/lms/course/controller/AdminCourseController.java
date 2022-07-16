package com.example.lms.course.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.admin.service.CategoryService;
import com.example.lms.course.dto.CourseDto;
import com.example.lms.course.model.CourseInput;
import com.example.lms.course.model.CourseParam;
import com.example.lms.course.service.CourseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@GetMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
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
	
	
	private String[] getNewSaveFile(String baseLocalPath, 
									String baseUrlPath, 
									String originalFilename) 
	{
		
		LocalDate now = LocalDate.now();
		
		String[] dirs = {	
			String.format("%s/%d/", baseLocalPath, now.getYear()),
			String.format("%s/%d/%02d/", baseLocalPath, 
							now.getYear(), now.getMonthValue()),
			String.format("%s/%d/%02d/%02d/", baseLocalPath, 
							now.getYear(), now.getMonthValue(), now.getDayOfMonth())};
		
		String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, 
							now.getYear(), now.getMonthValue(), now.getDayOfMonth());
		
		for(String dir : dirs) {
			File file = new File(dir);
			if (!file.isDirectory()) {
				file.mkdir();
			}
		}
		
		String fileExtension = "";
		if (originalFilename != null) {
			int dotPos = originalFilename.lastIndexOf(".");
			if (dotPos > -1) {
				fileExtension = originalFilename.substring(dotPos + 1);
			}
		}
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String newFilename = String.format("%s%s", dirs[2], uuid);
		String newUrlFilename = String.format("%s%s", urlDir, uuid);
		if (fileExtension.length() > 0) {
			newFilename += "." + fileExtension;
			newUrlFilename += "." + fileExtension;
		}
		
		String[] returnFilename = {newFilename, newUrlFilename};
		
		return returnFilename;
	}
	
	@PostMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
	public String addSubmit(Model model, 
							HttpServletRequest request, 
							MultipartFile file,
							CourseInput courseInput) 
	{
		
		String saveFilename = "";
		String urlFilename = "";
		
		if (file != null) {
			String originalFilename = file.getOriginalFilename();		
			String baseLocalPath = "C:/dev/LMS_Project/lms/files";
			String baseUrlPath = "/files";
			
			String urlPath = "/files/2022/07/16/8f85120b80c34460934a6d7b58407615.png";
			
			String[] arrFilename = getNewSaveFile(baseLocalPath, baseUrlPath, originalFilename);
			
			saveFilename = arrFilename[0];
			urlFilename = arrFilename[1];
			
			try {
				File newFile = new File(saveFilename);
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		courseInput.setFilename(saveFilename);
		courseInput.setUrlFilename(urlFilename);
		
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

	@PostMapping("/admin/course/delete.do")
	public String del(Model model, 
							HttpServletRequest request, 
							CourseInput courseInput) 
	{
		
		boolean result = courseSerivce.del(courseInput.getIdList());
		
		return "redirect:/admin/course/list.do";
	}
}
