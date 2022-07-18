package com.example.lms.admin.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
public class MemberHistoryDto {
	long id;
	LocalDateTime loginAt;
	String userIp;
	String userAgent;
	
	long totalCount;
	long seq;
	
	public String getLoginAtText() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		return loginAt != null ? loginAt.format(formatter) : "";
	}
}
