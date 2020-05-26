package com.auth.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth.payload.request.LoginRequest;
import com.auth.payload.request.SignUpRequest;
import com.auth.payload.response.ApiResponse;
import com.auth.payload.response.JwtAuthenticationResponse;
import com.auth.repository.RoleRepository;
import com.auth.repository.StateRepository;
import com.auth.repository.UserRepository;
import com.auth.security.JwtTokenProvider;
import com.auth.exception.AppException;
import com.auth.model.Role;
import com.auth.model.RoleName;
import com.auth.model.State;
import com.auth.model.StateName;
import com.auth.model.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
		
		//Check the username, email
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken"), HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		
		//Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(()-> new AppException("User Role not set."));
		
		user.setRoles(Collections.singleton(userRole));
		
		State userState = stateRepository.findByName(StateName.NORMAL_USER).orElseThrow(()-> new AppException("User State nte set."));
		
		user.setStates(Collections.singleton(userState));
		
		User result = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
		
		
		
		
	}

}
