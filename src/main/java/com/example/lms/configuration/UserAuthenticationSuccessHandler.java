package com.example.lms.configuration;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.example.lms.admin.mapper.MemberMapper;
import com.example.lms.member.model.MemberHistoryInput;
import com.example.lms.member.service.MemberHistoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final MemberMapper memberMapper;
	private final MemberHistoryService memberHistoryService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String userId = authentication.getName();
		memberMapper.updateLoginAt(userId, LocalDateTime.now());
		
		String userAgent = request.getHeader("user-agent");
		String clientIp = "";
		try {
			clientIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MemberHistoryInput historyInput = MemberHistoryInput.builder()
				.userId(userId)
				.loginAt(LocalDateTime.now())
				.userIp(clientIp)
				.userAgent(userAgent)
				.build();
		
		memberHistoryService.save(historyInput);	
		
		setDefaultTargetUrl("/");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
