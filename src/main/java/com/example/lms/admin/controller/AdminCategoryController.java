package com.example.lms.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.lms.admin.dto.CategoryDto;
import com.example.lms.admin.model.CategoryInput;
import com.example.lms.admin.model.MemberParam;
import com.example.lms.admin.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {
	
	private final CategoryService categoryService;

	@GetMapping("/admin/category/list.do")
	public String list(Model model, MemberParam memberParam) {
		
		List<CategoryDto> list = categoryService.list();
		model.addAttribute("list", list);
		
		return "admin/category/list";
	}
	
	@PostMapping("/admin/category/add.do")
	public String add(Model model, CategoryInput categoryInput) {
		
		boolean result = categoryService.add(categoryInput.getCategoryName());	
		return "redirect:/admin/category/list.do";
	}
}
