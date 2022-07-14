package com.example.lms.course.domain;

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
public class TakeCourse implements TakeCourseCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	long courseId;
	String userId;
	
	long payPrice; // 결제 금액
	String status; // 상태(수강신청, 결제완료, 수강취소)

	
	LocalDateTime createAt; // 신청일
}
