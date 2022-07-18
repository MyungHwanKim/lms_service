package com.example.lms.banner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.lms.banner.dto.BannerDto;
import com.example.lms.banner.model.BannerParam;

@Mapper
public interface BannerMapper {
	long selectListCount(BannerParam bannerParam);
	List<BannerDto> selectList(BannerParam bannerParam);
}
