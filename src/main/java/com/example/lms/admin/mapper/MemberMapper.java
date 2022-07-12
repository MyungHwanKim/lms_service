package com.example.lms.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lms.admin.dto.MemberDto;

@Mapper
public interface MemberMapper {

	List<MemberDto> selectList(MemberDto memberDto);
}
