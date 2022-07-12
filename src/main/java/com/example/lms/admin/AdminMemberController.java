package com.example.lms.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.lms.admin.dto.MemberDto;
import com.example.lms.admin.model.MemberParam;
import com.example.lms.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminMemberController {
	
	private final MemberService memberService;

	@GetMapping("/admin/member/list.do")
	public String list(Model model, MemberParam memberParam) {
		
		List<MemberDto> members = memberService.list(memberParam);
		model.addAttribute("list", members);
		
		return "admin/member/list";
	}
}
