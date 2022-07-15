package com.example.lms.course.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.lms.course.domain.TakeCourse;

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
public class TakeCourseDto {

	long id;
	
	long courseId;
	String userId;
	
	long payPrice; // 결제 금액
	String status; // 상태(수강신청, 결제완료, 수강취소)

	
	LocalDateTime createAt; // 신청일
	
	String userName;
	String phone;
	String subject;
	
	long totalCount;
	long seq;
	
	public String getCreateAtText() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		return createAt != null ? createAt.format(formatter) : "";
	}
	
	public static TakeCourseDto of(TakeCourse takeCourse) {
		return TakeCourseDto.builder()
							.id(takeCourse.getId())
							.courseId(takeCourse.getCourseId())
							.userId(takeCourse.getUserId())
							.payPrice(takeCourse.getPayPrice())
							.status(takeCourse.getStatus())
							.createAt(takeCourse.getCreateAt())
							.build();
	}
}
