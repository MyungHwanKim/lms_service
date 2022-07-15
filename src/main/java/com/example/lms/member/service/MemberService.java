package com.example.lms.member.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.lms.admin.dto.MemberDto;
import com.example.lms.admin.model.MemberParam;
import com.example.lms.course.model.ServiceResult;
import com.example.lms.member.model.MemberInput;
import com.example.lms.member.model.ResetPasswordInput;

public interface MemberService extends UserDetailsService {

	boolean register(MemberInput memberInput);
	
	/*
	 * uuid에 해당하는 계정을 활성화 함
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
	
	/*
	 * 회원 목록 리턴(관리자에서만 사용 가능)
	 */
	List<MemberDto> list(MemberParam memberParam);

	/*
	 * 회원 상세 정보
	 */
	MemberDto detail(String userId);

	/*
	 * 회원 상태 변경
	 */
	boolean updateStatus(String userId, String userStatus);

	/*
	 * 회원 비밀번호 초기화
	 */
	boolean updatePassword(String userId, String password);

	/*
	 * 회원정보 수정
	 */
	ServiceResult updateMember(MemberInput memberInput);
	
	/*
	 * 회원 정보 페이지 내 비밀번호 변경 기능
	 */
	ServiceResult updateMemberPassword(MemberInput memberInput);
}
