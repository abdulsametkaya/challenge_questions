package com.backend_challenge.questions.q6.controller;

import com.backend_challenge.questions.q6.dto.request.RegisterRequest;
import com.backend_challenge.questions.q6.dto.response.ResponseMessage;
import com.backend_challenge.questions.q6.dto.request.LoginRequest;
import com.backend_challenge.questions.q6.dto.response.CAResponse;
import com.backend_challenge.questions.q6.dto.response.LoginResponse;
import com.backend_challenge.questions.q6.service.CustomerService;
import com.backend_challenge.questions.q6.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJWTController {

	private CustomerService customerService;

	private AuthenticationManager authManager;

	private JwtUtils jwtUtils;

	@PostMapping("/register")
	public ResponseEntity<CAResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
		customerService.register(registerRequest);

		CAResponse response=new CAResponse();
		response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
		response.setSuccess(true);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@Valid  @RequestBody LoginRequest loginRequest){
	
	
		Authentication authentication= authManager.authenticate(new 
				UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

		String token= jwtUtils.generateJwtToken(authentication);
		
		LoginResponse response=new LoginResponse();
		response.setToken(token);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
