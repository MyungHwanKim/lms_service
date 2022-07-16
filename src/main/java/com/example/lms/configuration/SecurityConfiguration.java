package com.example.lms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.lms.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final MemberService memberService;
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserAuthenticationFailureHandler getFailureHandler() {
		return new UserAuthenticationFailureHandler();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
			
		web.ignoring().antMatchers("/files/**");
		super.configure(web);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();
		
		http.authorizeRequests()
			.antMatchers("/", 
					"/member/register", 
					"/member/email-auth",
					"/member/find-password",
					"/member/reset/password")
			.permitAll();
		
		http.authorizeRequests()
			.antMatchers("/admin/**")
			.hasAuthority("ROLE_ADMIN");
		
		http.formLogin()
			.loginPage("/member/login")
			.failureHandler(getFailureHandler())
			.permitAll();
			
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true);
		
		http.exceptionHandling()
			.accessDeniedPage("/error/denied");
			
		super.configure(http);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(memberService)
			.passwordEncoder(getPasswordEncoder());
		
		super.configure(auth);
	}
}
