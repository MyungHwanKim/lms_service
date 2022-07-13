package com.example.lms.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.lms.admin.dto.MemberDto;
import com.example.lms.admin.model.MemberInput;
import com.example.lms.admin.model.MemberParam;
import com.example.lms.course.controller.BaseController;
import com.example.lms.member.service.MemberService;
import com.example.lms.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminMemberController extends BaseController {
	
	private final MemberService memberService;

	@GetMapping("/admin/member/list.do")
	public String list(Model model, MemberParam memberParam) {
		
		memberParam.init();
		List<MemberDto> members = memberService.list(memberParam);
		
		long totalCount = 0;
		if (members != null && members.size() > 0) {
			totalCount = members.get(0).getTotalCount();
		}
		
		String queryString = memberParam.getQueryString();
		String pageHtml  = getPaperHtml(totalCount, 
										memberParam.getPageSize(), 
										memberParam.getPageIndex(), 
										queryString);
		model.addAttribute("list", members);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pager", pageHtml);
		
		return "admin/member/list";
	}
	
	@GetMapping("/admin/member/detail.do")
	public String detail(Model model, MemberParam memberParam) {
		
		memberParam.init();
		
		MemberDto member = memberService.detail(memberParam.getUserId());
		model.addAttribute("member", member);
		
		return "admin/member/detail";
	}
	
	@PostMapping("/admin/member/status.do")
	public String status(Model model, MemberInput memberInput) {
		
		boolean result = memberService.updateStatus(memberInput.getUserId(), memberInput.getUserStatus());
		
		return "redirect:/admin/member/detail.do?userId=" + memberInput.getUserId();
	}
	
	@PostMapping("/admin/member/password.do")
	public String password(Model model, MemberInput memberInput) {
		
		boolean result = memberService.updatePassword(memberInput.getUserId(), memberInput.getPassword());
		
		return "redirect:/admin/member/detail.do?userI	d=" + memberInput.getUserId();
	}
}
