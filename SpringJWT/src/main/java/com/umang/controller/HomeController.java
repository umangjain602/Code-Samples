package com.umang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.umang.bean.AuthorizationRequest;
import com.umang.bean.AuthorizationResponse;
import com.umang.service.MyUserDetailsService;
import com.umang.util.JwtUtil;

@RestController
public class HomeController {

	@Autowired
	AuthenticationManager authManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome Ordinary User.";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthorizationResponse> createAuthToken(@RequestBody AuthorizationRequest authReq){
		
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authReq.getUserName(),authReq.getPassword()));

		String jwt = jwtUtil.generateToken(myUserDetailsService.loadUserByUsername(authReq.getUserName()));
		
		return new ResponseEntity<AuthorizationResponse>(new AuthorizationResponse(jwt),HttpStatus.OK);
	}
	
}
