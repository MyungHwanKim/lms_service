package com.example.lms.banner.service;

import java.util.List;

import com.example.lms.banner.dto.BannerDto;
import com.example.lms.banner.model.BannerInput;
import com.example.lms.banner.model.BannerParam;

public interface BannerService {

	/*
	 * 강좌 등록
	 */
	boolean add(BannerInput bannerInput);

	/*
	 * 배너 정보 수정
	 */
	boolean set(BannerInput bannerInput);
	
	/*
	 * 배너 목록
	 */
	List<BannerDto> list(BannerParam bannerParam);

	/*
	 * 배너 상세 정보
	 */
	BannerDto getById(long id);

	/*
	 * 배너 내용 삭제
	 */
	boolean del(String idList);

	/*
	 * 전체 강좌 목록
	 */
	List<BannerDto> listAll();
}
