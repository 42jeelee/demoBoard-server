package kr.co.jeelee.demoboard.domain.auth.controller;

import kr.co.jeelee.demoboard.domain.auth.dto.LoginRequest;
import kr.co.jeelee.demoboard.domain.auth.service.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;

	@PostMapping(value = "/login")
	public ResponseEntity<Map<String, String>> login(
			@RequestBody LoginRequest loginRequest
	) {
		Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());

		try {
			String token = jwtProvider.generateToken(authenticationManager.authenticate(authenticationRequest));

			Map<String, String> map = new HashMap<>();
			map.put("token", token);
			return ResponseEntity.ok(map);
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
