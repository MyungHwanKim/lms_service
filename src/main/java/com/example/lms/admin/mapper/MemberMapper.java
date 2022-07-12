package com.example.lms.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lms.admin.dto.MemberDto;
import com.example.lms.admin.model.MemberParam;

@Mapper
public interface MemberMapper {

	List<MemberDto> selectList(MemberParam memberParam);
}
