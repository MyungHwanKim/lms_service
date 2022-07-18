package com.example.lms.banner.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.lms.banner.domain.Banner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BannerDto {

	private Long id;
	
	String bannerName;
	String url;
	String alterText;
	int open;
	
	String fileDirectory;
	
	int sortValue;
	boolean openYn;
	
	LocalDateTime createAt; // 등록일(추가날짜)
	LocalDateTime updateAt; // 수정일(수정날짜)
	
	long totalCount;
	long seq;
	
	public static BannerDto of(Banner banner) {
		return BannerDto.builder()
						.id(banner.getId())
						.bannerName(banner.getBannerName())
						.url(banner.getUrl())
						.alterText(banner.getAlterText())
						.open(banner.getOpen())
						.fileDirectory(banner.getFileDirectory())
						.sortValue(banner.getSortValue())
						.openYn(banner.isOpenYn())
						.createAt(banner.getCreateAt())
						.updateAt(banner.getUpdateAt())
						.build();
	}
	
	public String getCreateAtText() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		return createAt != null ? createAt.format(formatter) : "";
	}
	
	public String getUpdateAtText() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		return updateAt != null ? updateAt.format(formatter) : "";
	}

	public static List<BannerDto> of(List<Banner> bannerList) {
		
		if (bannerList == null) {
			return null;
		}
		
		List<BannerDto> bannerDtoList = new ArrayList<>();
		for(Banner x: bannerList) {
			bannerDtoList.add(BannerDto.of(x));
		}
		return bannerDtoList;
	}
}
