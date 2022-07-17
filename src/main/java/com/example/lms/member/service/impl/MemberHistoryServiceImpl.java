package com.example.lms.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.lms.admin.dto.MemberDto;
import com.example.lms.admin.dto.MemberHistoryDto;
import com.example.lms.admin.mapper.MemberHistoryMapper;
import com.example.lms.admin.model.MemberParam;
import com.example.lms.member.domain.MemberHistory;
import com.example.lms.member.model.MemberHistoryInput;
import com.example.lms.member.repository.MemberHistoryRepository;
import com.example.lms.member.service.MemberHistoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberHistoryServiceImpl implements MemberHistoryService {

	private final MemberHistoryRepository memberHistoryRepository;
	private final MemberHistoryMapper memberHistoryMapper;
	@Override
	public boolean save(MemberHistoryInput memberHistoryInput) {
		
		MemberHistory memberHistory = MemberHistory.builder()
				.userId(memberHistoryInput.getUserId())
				.loginAt(memberHistoryInput.getLoginAt())
				.userIp(memberHistoryInput.getUserIp())
				.userAgent(memberHistoryInput.getUserAgent())
				.build();
		memberHistoryRepository.save(memberHistory);
		
		return true;
	}
	@Override
	public List<MemberHistoryDto> myHistoryList(MemberParam memberParam) {		
		return memberHistoryMapper.selectListMyHistory(memberParam.getUserId());
	}


	
	
}
