package com.example.lms.member.domain;

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
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class MemberHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	private String userId;
	
	LocalDateTime loginAt;
	String userIp;
	String userAgent;
}
