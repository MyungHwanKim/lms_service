package com.example.lms.course.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	long categoryId;
	
	String imagePath;
	String keyword;
	String subject;
	
	@Column(length = 1000)
	String summary;
	
	@Lob
	String content;
	long price;
	long salePrice;
	LocalDate saleEndAt;
	
	LocalDateTime createAt; // 등록일(추가날짜)
	LocalDateTime updateAt; // 수정일(수정날짜)
	
	String filename;
	String urlFilename;
}
