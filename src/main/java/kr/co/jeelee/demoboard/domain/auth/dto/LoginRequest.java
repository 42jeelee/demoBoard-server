package kr.co.jeelee.demoboard.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "email cannot be blank.") String email,
        @NotBlank(message = "password cannot be blank.") String password
) {}
