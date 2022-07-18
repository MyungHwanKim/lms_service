package com.example.lms.banner.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BannerInput {
	
	Long id;
	
	String bannerName;
	String url;
	String alterText;
	int open;
	String fileDirectory;
	int sortValue;
	boolean openYn;
	
	String idList;
}
