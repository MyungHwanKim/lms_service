package com.example.lms.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		return "index"; 
	}
	
	@RequestMapping("/error/denied")
	public String errorDenied() {		
		return "error/denied";
	}
}
