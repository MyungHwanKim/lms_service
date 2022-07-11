package com.example.lms.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lms.member.model.MemberInput;
import com.example.lms.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;

	@RequestMapping("/member/login")
	public String login() {	
		return "member/login";
	}
	
	@GetMapping("/member/register")
	public String register() {
		
		System.out.println("request get");
		
		return "member/register";
	}
	
	@PostMapping("/member/register")
	public String registerSubmit(Model model, HttpServletRequest request, 
			HttpServletResponse response,
			MemberInput memberInput) {

		boolean result = memberService.register(memberInput);
		model.addAttribute("result", result);
		
		return "member/register_complete";
	}
	
	@GetMapping("/member/email-auth")
	public String emailAuth(Model model, HttpServletRequest request) {
		String uuid = request.getParameter("id");
		System.out.println(uuid);
		
		boolean result = memberService.emailAuth(uuid);
		model.addAttribute("result", result);
		
		return "member/email_auth";
	}
	
	@GetMapping("/member/info")
	public String memberInfo() {
		return "member/info";
	}
}
