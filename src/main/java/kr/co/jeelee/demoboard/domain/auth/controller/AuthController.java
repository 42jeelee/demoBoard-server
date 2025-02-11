package kr.co.jeelee.demoboard.domain.auth.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.jeelee.demoboard.domain.auth.service.JwtProvider;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final JwtProvider jwtProvider;

	@GetMapping(value = "/encode")
	public List<String> generateToken(
		@RequestParam String subject
	) {
		return List.of(jwtProvider.generateToken(subject));
	}

	@GetMapping(value = "/decode")
	public List<String> decodeToken(
		@RequestParam String token
	) {
		return List.of(jwtProvider.getSubject(token));
	}

}
