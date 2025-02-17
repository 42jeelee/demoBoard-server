package kr.co.jeelee.demoboard.domain.auth.service;

import kr.co.jeelee.demoboard.domain.auth.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;

    @Override
    public TokenDTO generateToken(final Authentication authentication) {
        String accessToken = jwtProvider.generateAccessToken(authentication);
        String refreshToken = jwtProvider.generateRefreshToken(authentication);

        return TokenDTO.of(accessToken, refreshToken);
    }

    @Override
    public TokenDTO refresh(final TokenDTO tokenDTO) {
        String newToken = jwtProvider.refresh(tokenDTO.accessToken(), tokenDTO.refreshToken());

        return TokenDTO.of(newToken);
    }

    @Override
    public boolean validateToken(final String accessToken) {
        return false;
    }
}
