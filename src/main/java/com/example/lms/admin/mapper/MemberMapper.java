package com.example.lms.admin.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lms.admin.dto.MemberDto;
import com.example.lms.admin.model.MemberParam;

@Mapper
public interface MemberMapper {

	long selectListCount(MemberParam memberParam);
	List<MemberDto> selectList(MemberParam memberParam);
	int updateLoginAt(String userId, LocalDateTime lastLoginAt);
}
