package com.example.lms.course.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ServiceResult {

	boolean result;
	String message;

	public ServiceResult(boolean result) {
		this.result = result;
	}
}
