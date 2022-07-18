package com.example.lms.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lms.banner.service.BannerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final BannerService bannerService;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("list", bannerService.listAll());
		return "index"; 
	}
	
	@RequestMapping("/error/denied")
	public String errorDenied() {		
		return "error/denied";
	}
}
