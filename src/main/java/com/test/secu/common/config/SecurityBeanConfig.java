package com.test.secu.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.test.secu.user.service.UserInfoService;


@Configuration
public class SecurityBeanConfig {
	@Autowired
	private UserInfoService userService;
		
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> {
			web.ignoring()
			.antMatchers("/css/**", "/js/**", "/imgs/**", "/resources/**");
		};
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity hs) throws Exception {
		hs.authorizeHttpRequests(req->req
				.antMatchers("/login", "/join", "/html/join", "/html/login")
				.permitAll()
				.anyRequest().authenticated())
		.formLogin(formLogin->formLogin
				.loginPage("/html/login")
				.usernameParameter("uiId")
				.passwordParameter("uiPwd")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/html/login-fail"))
		.csrf(csrf->csrf.disable())	
		.userDetailsService(userService);
				
		return hs.build();
	}
}
