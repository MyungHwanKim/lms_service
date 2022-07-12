package com.example.lms.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.lms.admin.dto.MemberDto;
import com.example.lms.admin.model.MemberParam;
import com.example.lms.member.service.MemberService;
import com.example.lms.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminMemberController {
	
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
		
		PageUtil pageUtil  = new PageUtil(totalCount, memberParam.getPageSize(), memberParam.getPageIndex(), queryString);
		model.addAttribute("list", members);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pager", pageUtil.pager());
		
		return "admin/member/list";
	}
}
