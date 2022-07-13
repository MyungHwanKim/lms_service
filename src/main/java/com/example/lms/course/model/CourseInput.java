package com.example.lms.course.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseInput {
	
	long id;
	long categoryId;
	String subject;
	String keyword;
	String summary;
	String content;
	long price;
	long salePrice;
	String saleEndAtText;
}
