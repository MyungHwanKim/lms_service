package com.example.lms.admin.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.lms.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDto {
	
	String userId;
	String userName;
	String phone;
	String password;
	LocalDateTime createAt;
	LocalDateTime updateAt;
	
	boolean emailAuthYn;
	LocalDateTime emailAuthAt;
	String eamilAuthKey;
	
	String resetPasswordKey;
	LocalDateTime resetPasswordLimitAt;
	
	boolean adminYn;
	String userStatus;
	
	private String zipcode;
	private String addr;
	private String addrDetail;
	
	long totalCount;
	long seq;
	
    public static MemberDto of(Member member) {
        
        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
                //.password(member.getPassword())
                .createAt(member.getCreateAt())
                .updateAt(member.getUpdateAt())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthAt(member.getEmailAuthAt())
                .eamilAuthKey(member.getEamilAuthKey())
                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitAt(member.getResetPasswordLimitAt())
                .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())
                
                .zipcode(member.getZipcode())
                .addr(member.getAddr())
                .addrDetail(member.getAddrDetail())
                .build();
    }
    
    public String getCreateAtText() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		return createAt != null ? createAt.format(formatter) : "";
	}
    
    public String getUpdateAtText() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		return updateAt != null ? updateAt.format(formatter) : "";
	}
}
