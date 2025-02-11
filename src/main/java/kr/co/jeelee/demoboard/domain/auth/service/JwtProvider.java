package kr.co.jeelee.demoboard.domain.auth.service;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {

	private final JwtEncoder jwtEncoder;
	private final JwtDecoder jwtDecoder;

	public String generateToken(final String subject) {
		Instant now = Instant.now();

		JwtClaimsSet claimsSet = JwtClaimsSet.builder()
			.subject(subject)
			.issuedAt(now)
			.expiresAt(now.plusSeconds(360))
			.build();

		return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
	}

	public String getSubject(String token) {
		return jwtDecoder.decode(token).getSubject();
	}
}
