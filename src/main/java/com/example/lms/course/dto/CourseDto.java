package com.example.lms.course.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.example.lms.course.domain.Course;

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
public class CourseDto {

	long id;
	long categoryId;
	String imagePath;
	String keyword;
	String subject;
	String summary;
	String content;
	long price;
	long salePrice;
	LocalDate saleEndAt;
	LocalDateTime createAt; // 등록일(추가날짜)
	LocalDateTime updateAt; // 수정일(수정날짜)
	
	long totalCount;
	long seq;
	
	public static CourseDto of(Course course) {	
		return CourseDto.builder()
						.id(course.getId())
						.categoryId(course.getCategoryId())
						.imagePath(course.getImagePath())
						.keyword(course.getKeyword())
						.subject(course.getSubject())
						.summary(course.getSummary())
						.content(course.getContent())
						.price(course.getPrice())
						.salePrice(course.getSalePrice())
						.saleEndAt(course.getSaleEndAt())
						.createAt(course.getCreateAt())
						.updateAt(course.getUpdateAt())
						.build();
	}

	public static List<CourseDto> of(List<Course> courseList) {
		
		if (courseList == null) {
			return null;
		}
		
		List<CourseDto> courseDtoList = new ArrayList<>();
		for(Course x: courseList) {
			courseDtoList.add(CourseDto.of(x));
		}
		return courseDtoList;
	}
}
