package kr.co.jeelee.demoboard.domain.auth.service;

import kr.co.jeelee.demoboard.domain.auth.dto.TokenDTO;
import org.springframework.security.core.Authentication;

public interface AuthService {

    TokenDTO generateToken(final Authentication authentication);

    TokenDTO refresh(final TokenDTO tokenDTO);

    boolean validateToken(final String accessToken);

}
