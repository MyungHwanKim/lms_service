package com.example.lms.admin.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	
	String userId;
	String userName;
	String phone;
	String password;
	LocalDateTime createAt; 
	
	boolean emailAuthYn;
	LocalDateTime emailAuthAt;
	String eamilAuthKey;
	
	String resetPasswordKey;
	LocalDateTime resetPasswordLimitAt;
	
	boolean adminYn;
}
