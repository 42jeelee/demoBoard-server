package kr.co.jeelee.demoboard.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TokenDTO(String accessToken, String refreshToken) {

    public static TokenDTO of(String accessToken, String refreshToken) {
        return new TokenDTO(accessToken, refreshToken);
    }

    public static TokenDTO of(String accessToken) {
        return new TokenDTO(accessToken, null);
    }
}
