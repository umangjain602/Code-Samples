package com.umang.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.umang.util.JwtUtil;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public JwtUtil getJwtUtil() {
		return new JwtUtil();
	}
	
}
