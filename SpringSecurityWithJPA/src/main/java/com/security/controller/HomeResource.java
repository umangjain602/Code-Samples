package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

	@GetMapping("/")
	public String helloAll() {
		return "Welcome to this portal.";
	}
	
	@GetMapping("/user")
	public String helloUser() {
		return "Welcome normal user.";
	}
	
	@GetMapping("/admin")
	public String helloAdmin() {
		return "Hello Most priviliged user aka Admin";
	}
}
