package com.example.lms.member.service;

import com.example.lms.member.model.MemberInput;

public interface MemberService {

	boolean register(MemberInput memberInput);
	
	/*
	 * uuid에 해당하는 계정을 활성화 함.
	 */
	boolean emailAuth(String uuid);
}
