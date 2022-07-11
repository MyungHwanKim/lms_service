package com.example.lms.member.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.lms.member.model.MemberInput;
import com.example.lms.member.model.ResetPasswordInput;

public interface MemberService extends UserDetailsService {

	boolean register(MemberInput memberInput);
	
	/*
	 * uuid에 해당하는 계정을 활성화 함.
	 */
	boolean emailAuth(String uuid);

	/*
	 * 입력한 이메일로 비밀번호 초기화 정보를 전송
	 */
	boolean sendResetPassword(ResetPasswordInput resetPasswordInput);

	/*
	 * 입력받은 uuid에 대해서 password로 초기화 함
	 */
	boolean sendResetPassword(String uuid, String password);

	/*
	 * 입력받은 uuid값이 유효한지 확인
	 */
	boolean checkResetPassword(String uuid);
}
