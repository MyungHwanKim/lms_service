package com.example.lms.course.model;

import com.example.lms.admin.model.CommonParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakeCourseParam extends CommonParam {

	long id;
	String status;
	
	String userId;
}
