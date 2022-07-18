package com.example.lms.banner.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String bannerName;
	private String url;
	private String alterText;
	private int open;
	
	private String fileDirectory;
	
	private int sortValue;
	private boolean openYn;
	
	private LocalDateTime createAt; // 등록일(추가날짜)
	private LocalDateTime updateAt; // 수정일(수정날짜)
}
