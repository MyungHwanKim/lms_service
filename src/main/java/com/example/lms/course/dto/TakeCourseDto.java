package com.example.lms.course.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
