package com.example.lms.member.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.lms.admin.dto.MemberDto;
import com.example.lms.admin.dto.MemberHistoryDto;
import com.example.lms.admin.model.MemberParam;
import com.example.lms.course.dto.TakeCourseDto;
import com.example.lms.course.model.CourseInput;
import com.example.lms.course.model.ServiceResult;
import com.example.lms.member.model.MemberHistoryInput;
import com.example.lms.member.model.MemberInput;
import com.example.lms.member.model.ResetPasswordInput;

public interface MemberHistoryService {
	
	/*
	 * 내 접속 기록 저장
	 */
	boolean save(MemberHistoryInput memberHistoryInput);
	
	/*
	 * 내 접속 기록 내역 목록
	 */
	List<MemberHistoryDto> myHistoryList(MemberParam memberParam);
}
