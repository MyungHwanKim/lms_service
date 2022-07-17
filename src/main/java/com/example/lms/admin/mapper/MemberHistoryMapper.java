package com.example.lms.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lms.admin.dto.MemberHistoryDto;

@Mapper
public interface MemberHistoryMapper {
	List<MemberHistoryDto> selectListMyHistory(String userId);
}
