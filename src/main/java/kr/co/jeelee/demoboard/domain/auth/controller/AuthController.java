package kr.co.jeelee.demoboard.domain.auth.controller;

import kr.co.jeelee.demoboard.domain.auth.dto.LoginRequest;
import kr.co.jeelee.demoboard.domain.auth.dto.TokenDTO;
import kr.co.jeelee.demoboard.domain.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final AuthService authService;

	@PostMapping(value = "/login")
	public ResponseEntity<TokenDTO> login(
			@RequestBody LoginRequest loginRequest
	) {
		Authentication authenticationRequest =
				UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());

		try {
			return ResponseEntity.ok(
					authService.generateToken(authenticationManager.authenticate(authenticationRequest))
			);
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
