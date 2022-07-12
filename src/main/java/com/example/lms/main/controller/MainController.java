package com.example.lms.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index() {		
		return "index";
	}
	
	@RequestMapping("/error/denied")
	public String errorDenied() {		
		return "error/denied";
	}
}
