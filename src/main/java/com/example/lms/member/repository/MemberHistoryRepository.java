package com.example.lms.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.member.domain.MemberHistory;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
	
}
