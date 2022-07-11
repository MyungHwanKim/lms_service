package com.example.lms.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	Optional<Member> findByEamilAuthKey(String emailAuthKey);
	
	Optional<Member> findByUserIdAndUserName(String userId, String userName);
	
	Optional<Member> findByResetPasswordKey(String resetPasswordKey);
}
