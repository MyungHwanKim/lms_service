package com.example.lms.course.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {

	long id;
	
	String imagePath;
	String keyword;
	String subject;
	String summary;
	String content;
	long price;
	long salePrice;
	LocalDateTime saleEndAt;
	LocalDateTime createAt; // 등록일(추가날짜)
	LocalDateTime updateAt; // 수정일(수정날짜)
	
	long totalCount;
	long seq;
}
