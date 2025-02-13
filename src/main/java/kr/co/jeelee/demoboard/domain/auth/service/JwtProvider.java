package kr.co.jeelee.demoboard.domain.auth.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {

	private final int ACCESS_TOKEN_EXP = 360;
	private final int REFRESH_TOKEN_EXP = 600;

	private final JwtEncoder jwtEncoder;
	private final JwtDecoder jwtDecoder;

	public String generateAccessToken(Authentication authentication) {

		Map<String, Object> claims = Map.of(
				"authorities",
				authentication.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(","))
		);

		return generateToken(authentication.getName(), claims, ACCESS_TOKEN_EXP);
	}

	public String generateRefreshToken(Authentication authentication) {
		return generateToken(authentication.getName(), Map.of(), REFRESH_TOKEN_EXP);
	}

	public String generateToken(final String subject, final Map<String, Object> claims, final int expiration) {
		Instant now = Instant.now();

		JwtClaimsSet claimsSet = JwtClaimsSet.builder()
				.subject(subject)
				.claims(claim -> claim.putAll(claims))
				.issuer("admin")
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiration))
				.build();

		return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
	}

	public String refresh(final String accessToken, final String refreshToken) throws JwtException {
		Jwt accessJwt = jwtDecoder.decode(accessToken);
		Jwt refreshJwt = jwtDecoder.decode(refreshToken);

		if (isExpired(refreshJwt) && !isRefreshToken(refreshJwt)) {
			throw new JwtException("Invalid refresh token");
		}

		if (
				!isExpired(accessJwt)
				|| !accessJwt.getSubject().equals(refreshJwt.getSubject())
				|| accessJwt.getClaim("authorities") == null
		) {
			throw new CustomException(ErrorCode.REQUEST_INVALID);
		}

		return generateToken(accessJwt.getSubject(), Map.of("authorities", accessJwt.getClaim("authorities")), ACCESS_TOKEN_EXP);
	}

	private boolean isExpired(final Jwt jwt) throws JwtException {
		try {
			return Objects.requireNonNull(jwt.getExpiresAt()).isBefore(Instant.now());
		} catch (NullPointerException e) {
			throw new JwtException("Expired or invalid JWT token");
		}
	}

	private boolean isRefreshToken(final Jwt jwt) throws JwtException {
		try {
			Instant issuedAt = Objects.requireNonNull(jwt.getIssuedAt());
			Instant expiresAt = Objects.requireNonNull(jwt.getExpiresAt());

			Duration diff = Duration.between(issuedAt, expiresAt);

			return Math.abs(diff.getSeconds() - REFRESH_TOKEN_EXP) < 10;
		} catch (NullPointerException e) {
			throw new JwtException("Expired or invalid JWT token");
		}

	}

}
