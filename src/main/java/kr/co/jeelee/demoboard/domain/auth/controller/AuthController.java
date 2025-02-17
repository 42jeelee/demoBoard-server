package kr.co.jeelee.demoboard.domain.auth.controller;

import kr.co.jeelee.demoboard.domain.auth.dto.LoginRequest;
import kr.co.jeelee.demoboard.domain.auth.dto.TokenDTO;
import kr.co.jeelee.demoboard.domain.auth.service.AuthService;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
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
	public TokenDTO login(
			@RequestBody LoginRequest loginRequest
	) {
		Authentication authenticationRequest =
				UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());

		try {
			return authService.generateToken(authenticationManager.authenticate(authenticationRequest));
		} catch (AuthenticationException e) {
			throw new CustomException(ErrorCode.UNAUTHORIZED);
		}
	}

	@PostMapping(value = "/auth/refresh")
	public TokenDTO refreshToken(
			@RequestBody TokenDTO tokenDTO
	) {
		return authService.refresh(tokenDTO);
	}

}
