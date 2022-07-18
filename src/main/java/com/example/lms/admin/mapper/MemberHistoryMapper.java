package com.example.lms.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lms.admin.dto.MemberHistoryDto;
import com.example.lms.admin.model.MemberParam;
import com.example.lms.banner.model.BannerParam;
import com.example.lms.member.model.MemberHistoryInput;

@Mapper
public interface MemberHistoryMapper {
	List<MemberHistoryDto> selectListMyHistory(String userId);
	
	long selectListCount(String userId);
}
